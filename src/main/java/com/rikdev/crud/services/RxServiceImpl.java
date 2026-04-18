package com.rikdev.crud.services;

import com.rikdev.crud.entities.Rx;
import com.rikdev.crud.repositories.RxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class RxServiceImpl implements RxService {
    // Aquí puedes implementar los métodos definidos en la interfaz RxService
    @Autowired
    RxRepository rxRepository;

    @Override
    public Rx saveRx(Rx rx) {
        return rxRepository.save(rx);
    }

    @Override
    public Rx updateRx(Rx rx) {
        return rxRepository.save(rx);
    }

    @Override
    public List<Rx> getRx() {
        return rxRepository.findAll();
    }

    @Override
    public Optional<Rx> getRxById(Long id) {
        return rxRepository.findById(id);
    }

    @Override
    public void deleteRx(Long id) {
        rxRepository.deleteById(id);
    }

    @Override
    public List<Rx> searchByDescription(String keyword) {
        System.out.println("Buscando dispositivos con el término:" + keyword);
        /*keyword = "VALIEN";*/
        return rxRepository.searchByDescription(keyword);
    }
}
