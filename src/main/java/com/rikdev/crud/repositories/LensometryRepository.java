package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.Lensometry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LensometryRepository extends JpaRepository<Lensometry, Long> {
    @Query(value = "SELECT * FROM device WHERE LOWER(description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Lensometry> searchByDescription(@Param("query") String query);
}
