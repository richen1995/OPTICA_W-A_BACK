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

@Table(name = "person")

public class Person {
  @Id // indica que es clave primaria
  @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincrementable
  private Long id_person;

  /* @NotNull */
  private String identification;

  /* @NotNull */
  private String first_name;

  /* @NotNull */
  private String last_name;

  /* @NotNull */
  private String f_birthdate;

  /* @NotNull */
  private String place_birth;

  /* @NotNull */
  private String phone;

  /* @NotNull */
  private String email;

  /* @NotNull */
  private String address;

  /* @NotNull */
  private String gender;

  /* @NotNull */
  private String charge;

  /* @NotNull */
  private String ocupation;

  /* @NotNull */
  private String provenance;

  private String f_creation;

  private String f_update;

  // 🔹 Relación con Historia Clínica
  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnoreProperties("person")
  private List<MedicalRecord> medicalRecords;

  // Relación con Usuario
  @OneToMany(mappedBy = "person", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JsonIgnoreProperties("person")
  private List<User> users;

}
