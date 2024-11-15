package com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest;

import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.aggregates.MedicalHistory;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.commands.DeleteMedicalHistoryCommand;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.model.queries.GetMedicalHistoryByIdQuery;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.services.MedicalHistoryCommandService;
import com.acme.nutrimove.platform.backend.medicalhistory.domain.services.MedicalHistoryQueryService;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources.CreateMedicalHistoryResource;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources.MedicalHistoryResource;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.resources.UpdateMedicalHistoryResource;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.transform.CreateMedicalHistoryCommandFromResourceAssembler;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.transform.MedicalHistoryResourceFromEntityAssembler;
import com.acme.nutrimove.platform.backend.medicalhistory.interfaces.rest.transform.UpdateMedicalHistoryCommandFromResourceAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/medical-history", produces = APPLICATION_JSON_VALUE)
public class MedicalHistoryController {

    private final MedicalHistoryCommandService commandService;
    private final MedicalHistoryQueryService queryService;
    private final CreateMedicalHistoryCommandFromResourceAssembler createAssembler;
    private final UpdateMedicalHistoryCommandFromResourceAssembler updateAssembler;
    private final MedicalHistoryResourceFromEntityAssembler resourceAssembler;

    public MedicalHistoryController(
            MedicalHistoryCommandService commandService,
            MedicalHistoryQueryService queryService,
            CreateMedicalHistoryCommandFromResourceAssembler createAssembler,
            UpdateMedicalHistoryCommandFromResourceAssembler updateAssembler,
            MedicalHistoryResourceFromEntityAssembler resourceAssembler) {
        this.commandService = commandService;
        this.queryService = queryService;
        this.createAssembler = createAssembler;
        this.updateAssembler = updateAssembler;
        this.resourceAssembler = resourceAssembler;
    }

    @PostMapping
    public ResponseEntity<MedicalHistoryResource> createMedicalHistory(@RequestBody CreateMedicalHistoryResource resource) {
        Optional<MedicalHistory> medicalHistory = commandService.handle(createAssembler.toCommand(resource));
        return medicalHistory.map(mh -> new ResponseEntity<>(resourceAssembler.toResourceFromEntity(mh), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    @GetMapping("/{id}/{userId}")
    public ResponseEntity<MedicalHistoryResource> getMedicalHistoryById(@PathVariable Long id, @PathVariable Long userId) {
        Optional<MedicalHistory> medicalHistory = queryService.handle(new GetMedicalHistoryByIdQuery(id, userId));
        return medicalHistory.map(mh -> ResponseEntity.ok(resourceAssembler.toResourceFromEntity(mh)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<MedicalHistoryResource>> getAllMedicalHistories() {
        List<MedicalHistory> medicalHistories = queryService.getAllMedicalHistories();
        List<MedicalHistoryResource> resources = medicalHistories.stream()
                .map(resourceAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MedicalHistoryResource> updateMedicalHistory(@PathVariable Long id, @RequestParam Long userId, @RequestBody UpdateMedicalHistoryResource resource) {
        Optional<MedicalHistory> updatedMedicalHistory = commandService.handle(updateAssembler.toCommand(id, userId, resource));
        return updatedMedicalHistory.map(mh -> ResponseEntity.ok(resourceAssembler.toResourceFromEntity(mh)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}/{userId}")
    public ResponseEntity<Void> deleteMedicalHistory(@PathVariable Long id, @PathVariable Long userId) {
        commandService.handle(new DeleteMedicalHistoryCommand(id, userId));
        return ResponseEntity.noContent().build();
    }
}
