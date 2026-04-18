package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.VisualAcuity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface VisualAcuityRepository extends JpaRepository<VisualAcuity, Long> {
    @Query(value = "SELECT * FROM device WHERE LOWER(description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<VisualAcuity> searchByDescription(@Param("query") String query);
}
