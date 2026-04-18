package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {

    @Query(value = "SELECT * FROM device WHERE LOWER(description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)

    List<Device> searchByDescription(@Param("query") String query);
}
