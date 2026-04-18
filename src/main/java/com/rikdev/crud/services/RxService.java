package com.rikdev.crud.services;

import com.rikdev.crud.entities.Rx;

import java.util.List;
import java.util.Optional;

public interface RxService {
    Rx saveRx(Rx rx);
    Rx updateRx(Rx rx);
    List<Rx> getRx();                  //obtener todas las historias clínicas
    Optional<Rx> getRxById(Long id);
    void deleteRx(Long id);
    List<Rx> searchByDescription(String keyword);

}
