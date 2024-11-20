package com.acme.nutrimove.platform.backend.achievements.interfaces.rest;

import com.acme.nutrimove.platform.backend.achievements.domain.model.aggregates.Achievement;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.CreateAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.model.commands.DeleteAchievementCommand;
import com.acme.nutrimove.platform.backend.achievements.domain.model.queries.GetAchievementByIdQuery;
import com.acme.nutrimove.platform.backend.achievements.domain.services.AchievementCommandService;
import com.acme.nutrimove.platform.backend.achievements.domain.services.AchievementQueryService;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources.AchievementResource;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources.CreateAchievementResource;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.resources.UpdateAchievementResource;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.transform.CreateAchievementCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.transform.AchievementResourceFromEntityAssembler;
import com.acme.nutrimove.platform.backend.achievements.interfaces.rest.transform.UpdateAchievementCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.user.domain.model.aggregates.User;
import com.acme.nutrimove.platform.backend.user.domain.services.UserQueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/achievements", produces = APPLICATION_JSON_VALUE)
public class AchievementController {

    private final AchievementCommandService commandService;
    private final AchievementQueryService queryService;
    private final CreateAchievementCommandFromResourceAssembler createAssembler;
    private final UpdateAchievementCommandFromResourceAssembler updateAssembler;
    private final AchievementResourceFromEntityAssembler resourceAssembler;
    private final UserQueryService userQueryService;

    public AchievementController(AchievementCommandService commandService,
                                 AchievementQueryService queryService,
                                 UserQueryService userQueryService,
                                 CreateAchievementCommandFromResourceAssembler createAssembler,
                                 UpdateAchievementCommandFromResourceAssembler updateAssembler,
                                 AchievementResourceFromEntityAssembler resourceAssembler) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.userQueryService = userQueryService;
        this.createAssembler = createAssembler;
        this.updateAssembler = updateAssembler;
        this.resourceAssembler = resourceAssembler;
    }


    @PostMapping
    public ResponseEntity<AchievementResource> createAchievement(@RequestBody CreateAchievementResource resource) {
        Optional<User> userOptional = userQueryService.findById(resource.userId());
        if (userOptional.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        User user = userOptional.get();

        CreateAchievementCommand command = createAssembler.toCommand(resource, user);

        Optional<Achievement> achievement = commandService.handle(command, user);

        return achievement.map(a -> new ResponseEntity<>(resourceAssembler.toResourceFromEntity(a), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}/{userId}")
    public ResponseEntity<AchievementResource> getAchievementById(@PathVariable Long id, @PathVariable Long userId) {
        Optional<Achievement> achievement = queryService.handle(new GetAchievementByIdQuery(id, userId));
        return achievement.map(a -> ResponseEntity.ok(resourceAssembler.toResourceFromEntity(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<AchievementResource>> getAllAchievements() {
        List<Achievement> achievements = queryService.getAllAchievements();
        List<AchievementResource> resources = achievements.stream()
                .map(resourceAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AchievementResource> updateAchievement(@PathVariable Long id, @RequestBody UpdateAchievementResource resource) {
        Optional<Achievement> updatedAchievement = commandService.handle(updateAssembler.toCommand(id, resource));
        return updatedAchievement.map(a -> ResponseEntity.ok(resourceAssembler.toResourceFromEntity(a)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAchievement(@PathVariable Long id) {
        commandService.handle(new DeleteAchievementCommand(id));
        return ResponseEntity.noContent().build();
    }
}