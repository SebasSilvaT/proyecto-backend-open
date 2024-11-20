package com.acme.nutrimove.platform.backend.goal.interfaces.rest;

import com.acme.nutrimove.platform.backend.goal.domain.model.aggregates.Goal;
import com.acme.nutrimove.platform.backend.goal.domain.model.commands.DeleteGoalCommand;
import com.acme.nutrimove.platform.backend.goal.domain.model.queries.GetGoalByIdQuery;
import com.acme.nutrimove.platform.backend.goal.domain.services.GoalCommandService;
import com.acme.nutrimove.platform.backend.goal.domain.services.GoalQueryService;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.resources.CreateGoalResource;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.resources.GoalResource;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.resources.UpdateGoalResource;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.transform.CreateGoalCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.transform.GoalResourceFromEntityAssembler;
import com.acme.nutrimove.platform.backend.goal.interfaces.rest.transform.UpdateGoalCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.services.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/goals", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Goals", description = "Operation related to Goals")
public class GoalController {

    private final GoalCommandService goalCommandService;
    private final GoalQueryService goalQueryService;
    private final UserQueryService userQueryService;

    public GoalController(GoalQueryService queryService, GoalCommandService commandService, UserQueryService userQueryService) {
        this.goalCommandService = commandService;
        this.goalQueryService = queryService;
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Create an Goal", description = "Create an Goal source with the provided news API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Goal created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })

    @PostMapping
    public ResponseEntity<GoalResource> createGoal(@RequestBody CreateGoalResource resource) {
        Optional<User> userOptional = userQueryService.findById(resource.userId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User user = userOptional.get();
        var command = CreateGoalCommandFromResourceAssembler.toCommand(resource, user);
        Optional<Goal> goal = goalCommandService.handle(command);
        return goal.map(source -> ResponseEntity.status(CREATED).body(GoalResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping({"{id}"})
    public ResponseEntity<GoalResource> getGoalById(@PathVariable Long id) {
        Optional<Goal> goal = goalQueryService.handle(new GetGoalByIdQuery(id));
        return goal.map(source -> ResponseEntity.ok(GoalResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<GoalResource>> getAllGoals() {
        var goals = goalQueryService.getAllGoals();
        if (goals.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var goalResources = goals.stream()
                .map(GoalResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(goalResources);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update an Goal", description = "Update an Goal source with the provided news API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Goal updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })

    public ResponseEntity<GoalResource> updateGoal(@PathVariable Long id, @RequestBody UpdateGoalResource resource) {
        Optional<Goal> updateGoal = goalCommandService.handle(UpdateGoalCommandFromResourceAssembler.toCommand(id, resource));
        return updateGoal.map(source -> ResponseEntity.ok(GoalResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());

    }


    @Operation(summary = "Delete an Goal", description = "Delete an Goal by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Goal deleted"),
            @ApiResponse(responseCode = "400", description = "Goal not found"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteGoal(@PathVariable Long id) {
        Optional<Goal> goal = goalQueryService.handle(new GetGoalByIdQuery(id));
        if (goal.isPresent()) {
            goalCommandService.handle(new DeleteGoalCommand(id));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}