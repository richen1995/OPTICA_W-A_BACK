package com.rikdev.crud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "rx")

public class Rx {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_rx;

    private String rx_desc_od;
    private String rx_desc_oi;
    private String rx_av_vl_od;
    private String rx_av_vl_oi;
    private String rx_add_od;
    private String rx_add_oi;
    private String rx_av_vp_od;
    private String rx_av_vp_oi;
    private String rx_dnp_od;
    private String rx_dnp_oi;
    private String observations;
    private String treatment;
    @Column(name = "id_medical_record", insertable = false, updatable = false)
    private Long id_medical_record; /* CLAVE FORANEA FK */
    private String f_creation; /* Date; */
    private String f_update; /* Date; */

    @ManyToOne
    @JoinColumn(name = "id_medical_record")
    @JsonIgnoreProperties({"lensometries", "visualAcuities", "rx"})
    private MedicalRecord medicalRecord;
}
