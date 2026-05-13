package com.rikdev.crud.services;

import com.rikdev.crud.entities.Refraction;

import java.util.List;
import java.util.Optional;

public interface RefractionService {
    Refraction saveRefraction(Refraction refraction);
    Refraction updateRefraction(Refraction refraction);
    List<Refraction> getRefractions();
    Optional<Refraction> getRefractionById(Long id);
    void deleteRefraction(Long id);
}
