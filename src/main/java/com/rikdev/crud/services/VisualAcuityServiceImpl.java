package com.rikdev.crud.services;

import com.rikdev.crud.entities.VisualAcuity;
import com.rikdev.crud.repositories.VisualAcuityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisualAcuityServiceImpl implements VisualAcuityService {

    // Aquí puedes implementar los métodos definidos en la interfaz VisualAcuityService
    @Autowired
    VisualAcuityRepository visualAcuityRepository;

    @Override
    public VisualAcuity saveVisualAcuity(VisualAcuity visualAcuity) {
        return visualAcuityRepository.save(visualAcuity);
    }

    @Override
    public VisualAcuity updateVisualAcuity(VisualAcuity visualAcuity) {
        return visualAcuityRepository.save(visualAcuity);
    }

    @Override
    public List<VisualAcuity> getVisualAcuity() {
        return visualAcuityRepository.findAll();
    }

    @Override
    public Optional<VisualAcuity> getVisualAcuityById(Long id) {
        return visualAcuityRepository.findById(id);
    }

    @Override
    public void deleteVisualAcuity(Long id) {
        visualAcuityRepository.deleteById(id);
    }

    @Override
    public List<VisualAcuity> searchByDescription(String keyword) {
        System.out.println("Buscando dispositivos con el término:" + keyword);
        /*keyword = "VALIEN";*/
        return visualAcuityRepository.searchByDescription(keyword);
    }
    
    
}
