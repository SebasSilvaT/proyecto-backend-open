package com.acme.nutrimove.platform.backend.food.application.internal.commandservices;

import com.acme.nutrimove.platform.backend.food.domain.model.aggregates.Food;
import com.acme.nutrimove.platform.backend.food.domain.model.commands.CreateFoodCommand;
import com.acme.nutrimove.platform.backend.food.domain.model.commands.UpdateFoodCommand;
import com.acme.nutrimove.platform.backend.food.domain.services.FoodCommandService;
import com.acme.nutrimove.platform.backend.food.infrastructure.persistence.jpa.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FoodCommandServiceImpl implements FoodCommandService {

    private final FoodRepository foodRepository;

    public FoodCommandServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Optional<Food> handle(CreateFoodCommand command) {

        Food food = new Food(
                command.user(),
                command.name(),
                command.calories(),
                command.proteins(),
                command.carbs(),
                command.fats()
        );
        Food savedFood = foodRepository.save(food);
        return Optional.of(savedFood);
    }

    @Override
    public Optional<Food> handle(UpdateFoodCommand command) {
        return foodRepository.findById(command.id()).map(food -> {
            food.setName(command.name());
            food.setCalories(command.calories());
            food.setProteins(command.proteins());
            food.setCarbs(command.carbs());
            food.setFats(command.fats());
            return foodRepository.save(food);
        });
    }

    @Override
    public void handleDelete(Long id) {
        foodRepository.deleteById(id);
    }
}
