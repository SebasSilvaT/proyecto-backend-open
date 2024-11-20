package com.acme.nutrimove.platform.backend.sleep.interfaces.rest;

import com.acme.nutrimove.platform.backend.sleep.domain.model.aggregates.Sleep;
import com.acme.nutrimove.platform.backend.sleep.domain.model.commands.DeleteSleepCommand;
import com.acme.nutrimove.platform.backend.sleep.domain.model.queries.GetSleepByIdQuery;
import com.acme.nutrimove.platform.backend.sleep.domain.services.SleepCommandService;
import com.acme.nutrimove.platform.backend.sleep.domain.services.SleepQueryService;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources.CreateSleepResource;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources.SleepResource;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.resources.UpdateSleepResource;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.transform.CreateSleepCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.transform.SleepResourceFromEntityAssembler;
import com.acme.nutrimove.platform.backend.sleep.interfaces.rest.transform.UpdateSleepCommandFromResourceAssembler;
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
@RequestMapping(value = "api/v1/sleep", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Sleep", description = "Operations related to Sleepers")
public class SleepController {

    private final SleepQueryService sleepQueryService;
    private final SleepCommandService sleepCommandService;
    private final UserQueryService userQueryService;

    public SleepController(SleepQueryService sleepQueryService,
                           SleepCommandService sleepCommandService,
                           UserQueryService userQueryService) {
        this.sleepQueryService = sleepQueryService;
        this.sleepCommandService = sleepCommandService;
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Create a sleep record", description = "Create a sleep record with the provided details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sleep record created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<SleepResource> createSleep(@RequestBody CreateSleepResource resource) {
        // Buscar al usuario por ID
        Optional<User> userOptional = userQueryService.findById(resource.userId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User user = userOptional.get();

        // Crear el comando
        var command = CreateSleepCommandFromResourceAssembler.toCommand(resource, user);

        // Ejecutar el comando y guardar la entidad Sleep
        Optional<Sleep> sleep = sleepCommandService.handle(command);
        return sleep.map(createdSleep -> new ResponseEntity<>(
                        SleepResourceFromEntityAssembler.toResourceFromEntity(createdSleep), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("{id}")
    public ResponseEntity<SleepResource> getSleepById(@PathVariable Long id) {
        Optional<Sleep> sleep = sleepQueryService.handle(new GetSleepByIdQuery(id));
        return sleep.map(foundSleep -> ResponseEntity.ok(SleepResourceFromEntityAssembler.toResourceFromEntity(foundSleep)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<SleepResource>> getAllSleepRecords() {
        var sleeps = sleepQueryService.getAllSleepRecords();
        if (sleeps.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var sleepResources = sleeps.stream()
                .map(SleepResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(sleepResources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<SleepResource> updateSleep(@PathVariable Long id, @RequestBody UpdateSleepResource resource) {
        var updateSleepCommand = UpdateSleepCommandFromResourceAssembler.toCommand(id, resource);
        var updatedSleep = sleepCommandService.handle(updateSleepCommand);
        return updatedSleep.map(sleep -> ResponseEntity.ok(SleepResourceFromEntityAssembler.toResourceFromEntity(sleep)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteSleep(@PathVariable Long id) {
        Optional<Sleep> sleep = sleepQueryService.handle(new GetSleepByIdQuery(id));
        if (sleep.isPresent()) {
            sleepCommandService.handle(new DeleteSleepCommand(id));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
