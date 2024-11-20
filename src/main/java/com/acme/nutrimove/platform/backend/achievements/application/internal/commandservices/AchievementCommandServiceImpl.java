package com.acme.nutrimove.platform.backend.achievements.application.internal.commandservices;

import com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates.Achievement;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.CreateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.UpdateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.DeleteAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.services.AchievementCommandService;
import com.acme.nutrimove.platform.backend.achievements.infrastructure.persistence.jpa.AchievementRepository;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AchievementCommandServiceImpl implements AchievementCommandService {

    private final AchievementRepository achievementRepository;

    public AchievementCommandServiceImpl(AchievementRepository achievementRepository) {
        this.achievementRepository = achievementRepository;
    }

    @Override
    public Optional<Achievement> handle(CreateAchievementCommand command, User user) {
        var achievement = new Achievement(command, user);
        return Optional.of(achievementRepository.save(achievement));
    }

    @Override
    public Optional<Achievement> handle(UpdateAchievementCommand command) {
        return achievementRepository.findByIdAndUserId(command.achievementId(), command.userId()).map(achievement -> {
            achievement.setAchievement(command.achievement());
            achievement.setDate(command.date());
            return achievementRepository.save(achievement);
        });
    }

    @Override
    public void handle(DeleteAchievementCommand command) {
        achievementRepository.deleteById(command.id());
    }
}