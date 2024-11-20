package com.acme.nutrimove.platform.backend.Hydration.interfaces.rest;

import com.acme.nutrimove.platform.backend.Hydration.domain.model.aggregates.Hydration;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.commands.DeleteHydrationCommand;
import com.acme.nutrimove.platform.backend.Hydration.domain.model.queries.GetHydrationById;
import com.acme.nutrimove.platform.backend.Hydration.domain.service.HydrationCommandService;
import com.acme.nutrimove.platform.backend.Hydration.domain.service.HydrationQueryService;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources.CreateHydrationResource;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources.HydrationResource;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.resources.UpdateHydrationResource;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.transform.CreateHydrationCommnadFromResourceAssembler;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.transform.HydrationResourceFromEntityAssembler;
import com.acme.nutrimove.platform.backend.Hydration.interfaces.rest.transform.UpdateHydrationCommandFromResourceAssembler;
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
@RequestMapping(value = "/api/v1/hydration", produces =  APPLICATION_JSON_VALUE)
@Tag(name = "Hydration", description = "Hydration API")
public class HydrationController {
    private final HydrationQueryService hydrationQueryService;
    private final HydrationCommandService hydrationCommandService;
    private final UserQueryService userQueryService;

    public HydrationController(HydrationQueryService hydrationQueryService, HydrationCommandService hydrationCommandService, UserQueryService userQueryService) {
        this.hydrationQueryService = hydrationQueryService;
        this.hydrationCommandService = hydrationCommandService;
        this.userQueryService = userQueryService;
    }

    @Operation(summary = "Create a Hydration", description = "Create a Hydration source with the provided news API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hydration created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })
    @PostMapping
    public ResponseEntity<HydrationResource> createHydration(@RequestBody CreateHydrationResource resource) {
        Optional<User> userOptional = userQueryService.findById(resource.userId());
        if (userOptional.isEmpty()) {

            return ResponseEntity.badRequest().build();
        }
        User user = userOptional.get();
        var command = CreateHydrationCommnadFromResourceAssembler.toCommand(resource, user);
        Optional<Hydration> hydration = hydrationCommandService.handle(command);
        return hydration.map(createdHydration -> new ResponseEntity<>(HydrationResourceFromEntityAssembler.toResourceFromEntity(createdHydration), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping({"{id}"})
    public ResponseEntity<HydrationResource> getHydrationById(@PathVariable Long id) {
        Optional<Hydration> hydration = hydrationQueryService.handle(new GetHydrationById(id));
        return hydration.map(source -> ResponseEntity.ok(HydrationResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<HydrationResource>> getAllHydrations() {
        var hydrations = hydrationQueryService.getAllHydration();
        if (hydrations.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var hydrationResources = hydrations.stream()
                .map(HydrationResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(hydrationResources);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a Hydration", description = "Update a Hydration source with the provided news API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Hydration updated"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
            @ApiResponse(responseCode = "404", description = "Not found"),
    })
    public ResponseEntity<HydrationResource> updateHydration(@PathVariable Long id, @RequestBody UpdateHydrationResource resource) {
        Optional<Hydration> updateHydration = hydrationCommandService.handle(UpdateHydrationCommandFromResourceAssembler.toCommand(id, resource));
        return updateHydration.map(source -> ResponseEntity.ok(HydrationResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Delete a Hydration", description = "Delete a Hydration by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Hydration deleted"),
            @ApiResponse(responseCode = "400", description = "Hydration not found"),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteHydration(@PathVariable Long id) {
        Optional<Hydration> hydration = hydrationQueryService.handle(new GetHydrationById(id));
        if (hydration.isPresent()) {
            hydrationCommandService.handle(new DeleteHydrationCommand(id));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
