package com.acme.nutrimove.platform.backend.medicalhistory.domain.model.aggregates;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Entity
@Table(name = "medical_history")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long userId;

    @Setter // Permite actualizar la fecha
    @Column(nullable = false)
    private LocalDate date;

    @Setter // Permite actualizar la condición médica
    @Column(name = "medical_condition", nullable = false)
    private String medicalCondition;

    @Setter // Permite actualizar la descripción
    @Column(nullable = false)
    private String description;

    // Constructor vacío requerido por JPA
    protected MedicalHistory() {}

    // Constructor con parámetros para inicialización
    public MedicalHistory(Long userId, LocalDate date, String medicalCondition, String description) {
        this.userId = userId;
        this.date = date;
        this.medicalCondition = medicalCondition;
        this.description = description;
    }
}
