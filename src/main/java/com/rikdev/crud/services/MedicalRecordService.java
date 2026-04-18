package com.rikdev.crud.services;

import com.rikdev.crud.entities.MedicalRecord;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface MedicalRecordService {
    MedicalRecord saveMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord saveFullMedicalRecord(MedicalRecord medicalRecord);
    MedicalRecord updateMedicalRecord(MedicalRecord medicalRecord);
    List<MedicalRecord> getMedicalRecords();                  //obtener todas las historias clínicas
    Optional<MedicalRecord> getMedicalRecordById(Long id);
    void deleteMedicalRecord(Long id);
    List<MedicalRecord> searchByDescription(String keyword);
    List<MedicalRecord> findWithFilter(String identification, LocalDate fdesde, LocalDate fhasta, String name);
}
