package com.acme.nutrimove.platform.backend.food.application.internal.queryservices;

import com.acme.nutrimove.platform.backend.food.domain.model.aggregates.Food;
import com.acme.nutrimove.platform.backend.food.domain.model.queries.GetFoodByIdQuery;
import com.acme.nutrimove.platform.backend.food.domain.model.queries.GetAllFoodsByUserQuery;
import com.acme.nutrimove.platform.backend.food.domain.services.FoodQueryService;
import com.acme.nutrimove.platform.backend.food.infrastructure.persistence.jpa.FoodRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FoodQueryServiceImpl implements FoodQueryService {

    private final FoodRepository foodRepository;

    public FoodQueryServiceImpl(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public Optional<Food> handle(GetFoodByIdQuery query) {
        return foodRepository.findById(query.foodId());
    }

    @Override
    public List<Food> handle(GetAllFoodsByUserQuery query) {
        return foodRepository.findByUser(query.user());
    }

    @Override
    public List<Food> getAllFoods() {
        return foodRepository.findAll();
    }
}
