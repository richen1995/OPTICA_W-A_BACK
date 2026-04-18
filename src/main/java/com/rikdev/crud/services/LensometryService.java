package com.rikdev.crud.services;

import com.rikdev.crud.entities.Lensometry;

import java.util.List;
import java.util.Optional;

public interface LensometryService {
    Lensometry saveLensometry(Lensometry lensometry);
    Lensometry updateLensometry(Lensometry lensometry);
    List<Lensometry> getLensometry();                  //obtener todas las historias clínicas
    Optional<Lensometry> getLensometryById(Long id);
    void deleteLensometry(Long id);
    List<Lensometry> searchByDescription(String keyword);

}
