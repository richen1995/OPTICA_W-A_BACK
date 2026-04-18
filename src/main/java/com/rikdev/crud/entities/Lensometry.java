package com.rikdev.crud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "lensometry")

public class Lensometry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_lensometry;

    private String lensometry_od_add;
    private String lensometry_av_vl_od;
    private String lensometry_av_vp_od;

    private String lensometry_oi_add;
    private String lensometry_av_vl_oi;
    private String lensometry_av_vp_oi;

    private String lensometry_ao_add;
    private String lensometry_av_vl_ao;
    private String lensometry_av_vp_ao;

    private String lens_type;
    private String lens_material;
    private String filter;
    private String time_using_rx;
    private String observation;
    @Column(name = "id_medical_record", insertable = false, updatable = false)
    private Long id_medical_record; /* CLAVE FORANEA FK */
    private String f_creation; /* Date; */
    private String f_update; /* Date; */

    // 🔹 Clave foránea hacia MedicalRecord
    @ManyToOne
    @JoinColumn(name = "id_medical_record")
    @JsonIgnoreProperties({ "lensometries", "visualAcuities", "rx" })
    private MedicalRecord medicalRecord;
}
