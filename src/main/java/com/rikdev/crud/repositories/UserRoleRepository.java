package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRoleRepository extends JpaRepository<UserRole, Long> {
    @Query(value = "SELECT * FROM user_role WHERE id_user = :userId", nativeQuery = true)
    List<UserRole> findByUserId(@Param("userId") Long userId);
}
