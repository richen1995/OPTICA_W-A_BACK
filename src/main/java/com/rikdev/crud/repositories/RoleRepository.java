package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);

    @Query(value = "SELECT * FROM role WHERE LOWER(name) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Role> searchByDescription(@Param("query") String query);
}
