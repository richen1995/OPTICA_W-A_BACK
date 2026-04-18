package com.rikdev.crud.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserRoleId implements Serializable {
    @Column(name = "id_user")
    private Long idUser;

    @Column(name = "id_role")
    private Long idRole;

    public UserRoleId(){}

    // constructor
    public UserRoleId(Long idUser, Long idRole) {
        this.idUser = idUser;
        this.idRole = idRole;
    }

    // getters and setters
    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getIdRole() {
        return idRole;
    }

    public void setIdRole(Long idRole) {
        this.idRole = idRole;
    }

    // IMPORTANTE para JPA
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserRoleId)) return false;
        UserRoleId that = (UserRoleId) o;
        return Objects.equals(idUser, that.idUser) &&
                Objects.equals(idRole, that.idRole);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUser, idRole);
    }
}
