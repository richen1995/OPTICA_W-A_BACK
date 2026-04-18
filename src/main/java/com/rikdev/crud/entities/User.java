package com.rikdev.crud.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })

@Table(name = "\"user\"")

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    @JsonProperty("id_user")
    private Long idUser;

    private String username;
    private String email_login;

    @com.fasterxml.jackson.annotation.JsonIgnore
    private String password_hash;

    private Boolean is_active;
    private LocalDateTime flast_login;
    private LocalDateTime fcreation;
    private Long id_person; /* CLAVE FORANEA FK */

    // 🔹 Clave foránea hacia Person
    @ManyToOne
    @JoinColumn(name = "id_person", insertable = false, updatable = false)
    @JsonIgnoreProperties("users")
    private Person person;

    // Relacion inversa con UserRole
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnoreProperties("user")
    private java.util.List<UserRole> userRoles;
}
