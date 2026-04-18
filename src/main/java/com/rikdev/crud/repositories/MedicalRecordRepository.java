package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.MedicalRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {
        @Query(value = "SELECT * FROM device WHERE LOWER(description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
        List<MedicalRecord> searchByDescription(@Param("query") String query);

        /*
         * @Query("SELECT m FROM MedicalRecord m JOIN m.person p " +
         * "WHERE (:id IS NULL OR p.identification = :id) " +
         * "AND m.f_creation >= COALESCE(:start, m.f_creation) " +
         * "AND m.f_creation <= COALESCE(:end, m.f_creation)")
         */
        @Query("SELECT m FROM MedicalRecord m JOIN m.person p " +
                        "WHERE (:id IS NULL OR p.identification = :id) " +
                        "AND m.f_creation >= COALESCE(:start, m.f_creation) " +
                        "AND m.f_creation <= COALESCE(:end, m.f_creation) " +
                        "AND (:name IS NULL OR LOWER(CONCAT(p.first_name, ' ', p.last_name)) LIKE LOWER(CONCAT('%', :name, '%')))"
                        +
                        "ORDER BY m.f_creation DESC")
        List<MedicalRecord> findWithFilters(
                        @Param("id") String id,
                        @Param("start") LocalDate start,
                        @Param("end") LocalDate end,
                        @Param("name") String name);

        @Query("SELECT m FROM MedicalRecord m ORDER BY m.f_creation DESC")
        List<MedicalRecord> findAllOrderedByDate();

}
