package com.rikdev.crud.repositories;

import com.rikdev.crud.entities.Refraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefractionRepository extends JpaRepository<Refraction, Long> {
}
