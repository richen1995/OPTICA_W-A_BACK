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

    private String ref_sphere_dynamic_od;
    private String ref_cylinder_dynamic_od;
    private String ref_axis_dynamic_od;

    private String ref_sphere_dynamic_oi;
    private String ref_cylinder_dynamic_oi;
    private String ref_axis_dynamic_oi;

    private String ref_sphere_static_od;
    private String ref_cylinder_static_od;
    private String ref_axis_static_od;

    private String ref_sphere_static_oi;
    private String ref_cylinder_static_oi;
    private String ref_axis_static_oi;

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
