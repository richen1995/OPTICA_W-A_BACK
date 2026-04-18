package com.rikdev.crud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "visual_acuity")

public class VisualAcuity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_visual_acuity;

    private String visual_acuity_vl_sc_od;
    private String visual_acuity_vl_sc_oi;
    private String visual_acuity_vl_sc_ao;
    private String visual_acuity_vl_cc_od;
    private String visual_acuity_vl_cc_oi;
    private String visual_acuity_vl_cc_ao;
    private String distancia_vl;

    private String visual_acuity_vp_sc_od;
    private String visual_acuity_vp_sc_oi;
    private String visual_acuity_vp_sc_ao;
    private String visual_acuity_vp_cc_od;
    private String visual_acuity_vp_cc_oi;
    private String visual_acuity_vp_cc_ao;
    private String distancia_vp;

    private String ph_od;
    private String ph_oi;
    private String ph_ao;

    private String dominance;
    @Column(name = "id_medical_record", insertable = false, updatable = false)
    private Long id_medical_record; /* CLAVE FORANEA FK */
    private String f_creation; /* Date; */
    private String f_update; /* Date; */

    @ManyToOne
    @JoinColumn(name = "id_medical_record")
    @JsonIgnoreProperties({ "lensometries", "visualAcuities", "rx" })
    private MedicalRecord medicalRecord;
}
