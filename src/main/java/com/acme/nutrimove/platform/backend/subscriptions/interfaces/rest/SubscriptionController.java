package com.acme.nutrimove.platform.backend.subscriptions.interfaces.rest;

import com.acme.nutrimove.platform.backend.subscriptions.domain.model.aggregates.Subscription;
import com.acme.nutrimove.platform.backend.subscriptions.domain.model.queries.GetSubscriptionByIdQuery;
import com.acme.nutrimove.platform.backend.subscriptions.domain.services.SubscriptionsCommandService;
import com.acme.nutrimove.platform.backend.subscriptions.domain.services.SubscriptionsQueryService;
import com.acme.nutrimove.platform.backend.subscriptions.interfaces.rest.resources.CreateSubscriptionResource;
import com.acme.nutrimove.platform.backend.subscriptions.interfaces.rest.resources.SubscriptionResource;
import com.acme.nutrimove.platform.backend.subscriptions.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.subscriptions.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
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
@RequestMapping(value = "api/v1/subscriptions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Subscriptions", description = "Operation related to Subscriptions")
public class SubscriptionController {
    private final SubscriptionsQueryService subscriptionsQueryService;
    private final SubscriptionsCommandService subscriptionsCommandService;

    public SubscriptionController(SubscriptionsQueryService subscriptionsQueryService, SubscriptionsCommandService subscriptionsCommandService) {
        this.subscriptionsQueryService = subscriptionsQueryService;
        this.subscriptionsCommandService = subscriptionsCommandService;
    }


    @Operation(summary = "Create an subscription", description = "Create an subscription source with the provided API")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Activity created"),
            @ApiResponse(responseCode = "400", description = "Bad Request"),
    })

    @PostMapping
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource resource) {
        Optional<Subscription> subscription = subscriptionsCommandService
                .handle(CreateSubscriptionCommandFromResourceAssembler.toCommand(resource));
        return subscription.map(source -> new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }


    @GetMapping("{id}")
    public ResponseEntity<SubscriptionResource> getSubscriptionById(@PathVariable Long id) {
        Optional<Subscription> subscription = subscriptionsQueryService.handle(new GetSubscriptionByIdQuery(id));
        return subscription.map(source -> ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(source)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("")
    public ResponseEntity<List<SubscriptionResource>> getAllActivities() {
        var subscriptions = subscriptionsQueryService.getAllActivities();
        if (subscriptions.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        var activityResources = subscriptions.stream()
                .map(SubscriptionResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(activityResources);
    }
}
