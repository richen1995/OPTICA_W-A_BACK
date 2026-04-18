package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.Rx;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RxRepository extends JpaRepository<Rx, Long> {
    @Query(value = "SELECT * FROM device WHERE LOWER(description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Rx> searchByDescription(@Param("query") String query);
}
