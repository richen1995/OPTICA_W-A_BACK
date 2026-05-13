package com.rikdev.crud.services;

import com.rikdev.crud.entities.Refraction;
import com.rikdev.crud.repositories.RefractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RefractionServiceImpl implements RefractionService {

    @Autowired
    private RefractionRepository refractionRepository;

    @Override
    public Refraction saveRefraction(Refraction refraction) {
        return refractionRepository.save(refraction);
    }

    @Override
    public Refraction updateRefraction(Refraction refraction) {
        return refractionRepository.save(refraction);
    }

    @Override
    public List<Refraction> getRefractions() {
        return refractionRepository.findAll();
    }

    @Override
    public Optional<Refraction> getRefractionById(Long id) {
        return refractionRepository.findById(id);
    }

    @Override
    public void deleteRefraction(Long id) {
        refractionRepository.deleteById(id);
    }
}
