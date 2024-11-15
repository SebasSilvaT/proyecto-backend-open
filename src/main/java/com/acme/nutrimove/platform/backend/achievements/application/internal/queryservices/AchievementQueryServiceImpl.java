package com.acme.nutrimove.platform.backend.achievements.application.internal.queryservices;

import com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates.Achievement;
import com.acme.nutrimove.platform.backend.achievements.domain.model.queries.GetAchievementByIdQuery;
import com.acme.nutrimove.platform.backend.achievements.domain.services.AchievementQueryService;
import com.acme.nutrimove.platform.backend.achievements.infrastructure.persistence.jpa.AchievementRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AchievementQueryServiceImpl implements AchievementQueryService {

    private final AchievementRepository achievementRepository;

    public AchievementQueryServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public List<Achievement> getAllAchievements() {
        return achievementRepository.findAll();
    }

    @Override
    public Optional<Achievement> handle(GetAchievementByIdQuery query) {
        return achievementRepository.findByIdAndUserId(query.achievementId(), query.userId());
    }
}
