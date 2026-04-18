package com.rikdev.crud.services;

import com.rikdev.crud.entities.VisualAcuity;

import java.util.List;
import java.util.Optional;

public interface VisualAcuityService {
    VisualAcuity saveVisualAcuity(VisualAcuity visualAcuity);
    VisualAcuity updateVisualAcuity(VisualAcuity visualAcuity);
    List<VisualAcuity> getVisualAcuity();                  //obtener todas las historias clínicas
    Optional<VisualAcuity> getVisualAcuityById(Long id);
    void deleteVisualAcuity(Long id);
    List<VisualAcuity> searchByDescription(String keyword);

}
