package com.acme.nutrimove.platform.backend.activities.interfaces.rest;

import com.acme.nutrimove.platform.backend.activities.domain.model.aggregates.Activity;
import com.acme.nutrimove.platform.backend.activities.domain.model.commands.CreateActivityCommand;
import com.acme.nutrimove.platform.backend.activities.domain.model.commands.DeleteActivityCommand;
import com.acme.nutrimove.platform.backend.activities.domain.model.queries.GetActivityByIdQuery;
import com.acme.nutrimove.platform.backend.activities.domain.model.queries.GetAllActivityByNameQuery;
import com.acme.nutrimove.platform.backend.activities.domain.services.ActivityCommandService;
import com.acme.nutrimove.platform.backend.activities.domain.services.ActivityQueryService;
import com.acme.nutrimove.platform.backend.activities.interfaces.rest.resources.ActivityResource;
import com.acme.nutrimove.platform.backend.activities.interfaces.rest.resources.CreateActivityResource;
import com.acme.nutrimove.platform.backend.activities.interfaces.rest.transform.ActivityResourceFromEntityAssembler;
import com.acme.nutrimove.platform.backend.activities.interfaces.rest.transform.CreateActivityCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.services.UserQueryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/activities", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Activities", description = "Operations related to Activities")
public class ActivityController {

    private final ActivityQueryService activityQueryService;
    private final ActivityCommandService activityCommandService;
    private final UserQueryService userQueryService;
    private final CreateActivityCommandFromResourceAssembler createActivityCommandFromResourceAssembler;

    // Constructor con inyección de dependencias
    public ActivityController(ActivityQueryService activityQueryService,
                              ActivityCommandService activityCommandService,
                              UserQueryService userQueryService,
                              CreateActivityCommandFromResourceAssembler createActivityCommandFromResourceAssembler) {
        this.activityQueryService = activityQueryService;
        this.activityCommandService = activityCommandService;
        this.userQueryService = userQueryService;
        this.createActivityCommandFromResourceAssembler = createActivityCommandFromResourceAssembler;
    }

    @Operation(summary = "Create an activity", description = "Create an activity for a specific user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<Activity> createActivity(@RequestBody CreateActivityResource resource) {
        // Buscar al usuario por ID
        Optional<User> userOptional = userQueryService.findById(resource.userId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }

        User user = userOptional.get();
        CreateActivityCommand command = createActivityCommandFromResourceAssembler.toCommand(resource, user);

        Optional<Activity> activityOptional = activityCommandService.handle(command);
        return activityOptional
                .map(activity -> ResponseEntity.status(HttpStatus.CREATED).body(activity))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @Operation(summary = "Get activities by name", description = "Retrieve all activities with a specific name")
    @GetMapping("/search")
    public ResponseEntity<List<ActivityResource>> getAllActivityByName(@RequestParam String name) {
        var getAllActivityByNameQuery = new GetAllActivityByNameQuery(name);
        var activities = activityQueryService.handle(getAllActivityByNameQuery);
        if (activities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var activityResources = activities.stream()
                .map(ActivityResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(activityResources);
    }

    @Operation(summary = "Get an activity by ID", description = "Retrieve an activity by its ID")
    @GetMapping("/{id}")
    public ResponseEntity<ActivityResource> getActivityById(@PathVariable Long id) {
        Optional<Activity> activity = activityQueryService.handle(new GetActivityByIdQuery(id));
        return activity.map(a -> ResponseEntity.ok(ActivityResourceFromEntityAssembler.toResourceFromEntity(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Get all activities", description = "Retrieve all activities")
    @GetMapping
    public ResponseEntity<List<ActivityResource>> getAllActivities() {
        var activities = activityQueryService.getAllActivities();
        if (activities.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var activityResources = activities.stream()
                .map(ActivityResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(activityResources);
    }

    @Operation(summary = "Delete an activity", description = "Delete an activity by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Activity deleted"),
            @ApiResponse(responseCode = "404", description = "Activity not found"),
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        Optional<Activity> activity = activityQueryService.handle(new GetActivityByIdQuery(id));
        if (activity.isPresent()) {
            activityCommandService.handle(new DeleteActivityCommand(id));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
