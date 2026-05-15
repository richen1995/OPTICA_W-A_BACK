package com.rikdev.crud.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.*;

@Data
@NoArgsConstructor
@Entity
@Table(name = "refraction")
public class Refraction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_refraction;

    private String ref_sph_cyl_ax_dym_od;
    private String ref_sph_cyl_ax_dym_oi;
    private String ref_sph_cyl_ax_stat_od;
    private String ref_sph_cyl_ax_stat_oi;

    @Column(name = "id_medical_record", insertable = false, updatable = false)
    private Long id_medical_record; /* CLAVE FORANEA FK */
    private String f_creation; /* Date; */
    private String f_update; /* Date; */

    // 🔹 Clave foránea hacia MedicalRecord
    @ManyToOne
    @JoinColumn(name = "id_medical_record")
    @JsonIgnoreProperties({ "refractions", "lensometries", "visualAcuities", "rx" })
    private MedicalRecord medicalRecord;
}
