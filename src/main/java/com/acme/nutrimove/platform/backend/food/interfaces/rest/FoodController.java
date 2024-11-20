package com.acme.nutrimove.platform.backend.food.interfaces.rest;

import com.acme.nutrimove.platform.backend.food.domain.model.aggregates.Food;
import com.acme.nutrimove.platform.backend.food.domain.model.commands.CreateFoodCommand;
import com.acme.nutrimove.platform.backend.food.domain.model.commands.UpdateFoodCommand;
import com.acme.nutrimove.platform.backend.food.domain.model.queries.GetFoodByIdQuery;
import com.acme.nutrimove.platform.backend.food.domain.services.FoodCommandService;
import com.acme.nutrimove.platform.backend.food.domain.services.FoodQueryService;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.resources.CreateFoodResource;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.resources.FoodResource;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.resources.UpdateFoodResource;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.transform.FoodResourceFromEntityAssembler;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.transform.CreateFoodCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.food.interfaces.rest.transform.UpdateFoodCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.services.UserQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "api/v1/foods", produces = APPLICATION_JSON_VALUE)
public class FoodController {

    private final FoodQueryService foodQueryService;
    private final FoodCommandService foodCommandService;
    private final UserQueryService userQueryService;

    public FoodController(FoodQueryService foodQueryService,
                          FoodCommandService foodCommandService,
                          UserQueryService userQueryService) {
        this.foodQueryService = foodQueryService;
        this.foodCommandService = foodCommandService;
        this.userQueryService = userQueryService;
    }

    @PostMapping
    public ResponseEntity<FoodResource> createFood(@RequestBody CreateFoodResource resource) {
        Optional<User> userOptional = userQueryService.findById(resource.userId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User user = userOptional.get();

        CreateFoodCommand command = CreateFoodCommandFromResourceAssembler.toCommand(resource, user);
        Optional<Food> food = foodCommandService.handle(command);
        return food.map(f -> new ResponseEntity<>(FoodResourceFromEntityAssembler.toResourceFromEntity(f), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FoodResource> getFoodById(@PathVariable Long id) {
        Optional<Food> food = foodQueryService.handle(new GetFoodByIdQuery(id));
        return food.map(f -> ResponseEntity.ok(FoodResourceFromEntityAssembler.toResourceFromEntity(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<FoodResource>> getAllFoods() {
        List<Food> foods = foodQueryService.getAllFoods();
        if (foods.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        List<FoodResource> resources = foods.stream()
                .map(FoodResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FoodResource> updateFood(@PathVariable Long id, @RequestBody UpdateFoodResource resource) {
        UpdateFoodCommand command = UpdateFoodCommandFromResourceAssembler.toCommand(id, resource);
        Optional<Food> updatedFood = foodCommandService.handle(command);
        return updatedFood.map(f -> ResponseEntity.ok(FoodResourceFromEntityAssembler.toResourceFromEntity(f)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFood(@PathVariable Long id) {
        Optional<Food> food = foodQueryService.handle(new GetFoodByIdQuery(id));
        if (food.isPresent()) {
            foodCommandService.handleDelete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
