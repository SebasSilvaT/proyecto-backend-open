package com.acme.nutrimove.platform.backend.achievements.domain.services;

import com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates.Achievement;
import com.acme.nutrimove.platform.backend.achievements.domain.model.queries.GetAchievementByIdQuery;

import java.util.List;
import java.util.Optional;

public interface AchievementQueryService {

    List<Achievement> getAllAchievements();

    Optional<Achievement> handle(GetAchievementByIdQuery query);
}
