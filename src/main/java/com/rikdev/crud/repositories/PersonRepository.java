package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query(value = "SELECT * FROM device WHERE LOWER(description) LIKE LOWER(CONCAT('%', :query, '%'))", nativeQuery = true)
    List<Person> searchByDescription(@Param("query") String query);

    Optional<Person> findByIdentification(String identification);
}
