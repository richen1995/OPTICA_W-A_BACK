package com.rikdev.crud.services;

import com.rikdev.crud.entities.MedicalRecord;
import com.rikdev.crud.repositories.MedicalRecordRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class MedicalRecordServiceImpl implements MedicalRecordService {

    @Autowired
    MedicalRecordRepository medicalRecordRepository;

    @Override
    public MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord saveFullMedicalRecord(MedicalRecord medicalRecord) {
        // 🔹 Normalizar también el ID principal de la Historia Clínica si es 0
        if (medicalRecord.getId_medical_record() != null && medicalRecord.getId_medical_record() == 0L) {
            medicalRecord.setId_medical_record(null);
        }

        // 🔹 Normalizar IDs en 0 a null para evitar errores de Optimistic Locking
        if (medicalRecord.getLensometries() != null) {
            medicalRecord.getLensometries().forEach(l -> {
                if (l.getId_lensometry() != null && l.getId_lensometry() == 0L) {
                    l.setId_lensometry(null);
                }
                l.setMedicalRecord(medicalRecord);
            });
        }

        if (medicalRecord.getVisualAcuities() != null) {
            medicalRecord.getVisualAcuities().forEach(va -> {
                if (va.getId_visual_acuity() != null && va.getId_visual_acuity() == 0L) {
                    va.setId_visual_acuity(null);
                }
                va.setMedicalRecord(medicalRecord);
            });
        }

        if (medicalRecord.getRx() != null) {
            medicalRecord.getRx().forEach(rx -> {
                if (rx.getId_rx() != null && rx.getId_rx() == 0L) {
                    rx.setId_rx(null);
                }
                rx.setMedicalRecord(medicalRecord);
            });
        }

        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord) {
        return medicalRecordRepository.save(medicalRecord);
    }

    @Override
    public List<MedicalRecord> getMedicalRecords() {
        return medicalRecordRepository.findAllOrderedByDate();
    }

    @Override
    public Optional<MedicalRecord> getMedicalRecordById(Long id) {
        return medicalRecordRepository.findById(id);
    }

    @Override
    public void deleteMedicalRecord(Long id) {
        medicalRecordRepository.deleteById(id);
    }

    @Override
    public List<MedicalRecord> searchByDescription(String keyword) {
        System.out.println("Buscando dispositivos con el término:" + keyword);
        /* keyword = "VALIEN"; */
        return medicalRecordRepository.searchByDescription(keyword);
    }

    @Override
    public List<MedicalRecord> findWithFilter(String identification, LocalDate fdesde, LocalDate fhasta, String name) {
        String cedula = (identification == null || identification.trim().isEmpty())
                ? null
                : identification.trim();

        return medicalRecordRepository.findWithFilters(cedula, fdesde, fhasta, name);
    }

}
