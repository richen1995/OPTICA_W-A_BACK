package com.rikdev.crud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

@Table(name = "medical_record")

public class MedicalRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_medical_record;
    private String uses_glasses;
    private String last_checkup;
    private String reasons_for_visit;
    private String previous_illnesses;
    private String ph_ocular;
    private String ph_general;
    private String fh_ocular;
    private String fh_general;
    private Long id_person; /* CLAVE FORANEA FK */
    private String f_creation;/* Date; */
    private String f_update;/* Date; */

    // 🔹 Relación con Persona
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_person", insertable = false, updatable = false)
    @JsonIgnoreProperties("medicalRecords")
    private Person person;

    // 🔹 Relación con Lensometry
    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties("medicalRecord")
    private List<Lensometry> lensometries;

    // 🔹 Relación con VisualAcuity
    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties("medicalRecord")
    private List<VisualAcuity> visualAcuities;

    // 🔹 Relación con Rx
    @OneToMany(mappedBy = "medicalRecord", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties("medicalRecord")
    private List<Rx> rx;
}
