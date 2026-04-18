package com.rikdev.crud.services;

import com.rikdev.crud.entities.Lensometry;
import com.rikdev.crud.repositories.LensometryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LensometryServiceImpl implements LensometryService{
    @Autowired
    LensometryRepository lensometryRepository;

    @Override
    public Lensometry saveLensometry(Lensometry lensometry) {
        return lensometryRepository.save(lensometry);
    }

    @Override
    public Lensometry updateLensometry(Lensometry lensometry) {
        return lensometryRepository.save(lensometry);
    }

    @Override
    public List<Lensometry> getLensometry() {
        return lensometryRepository.findAll();
    }

    @Override
    public Optional<Lensometry> getLensometryById(Long id) {
        return lensometryRepository.findById(id);
    }

    @Override
    public void deleteLensometry(Long id) {
        lensometryRepository.deleteById(id);
    }

    @Override
    public List<Lensometry> searchByDescription(String keyword) {
        System.out.println("Buscando dispositivos con el término:" + keyword);
        /*keyword = "VALIEN";*/
        return lensometryRepository.searchByDescription(keyword);
    }
}
