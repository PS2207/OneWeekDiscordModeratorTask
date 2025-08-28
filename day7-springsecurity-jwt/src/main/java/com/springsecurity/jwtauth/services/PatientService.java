package com.springsecurity.jwtauth.services;

import java.util.List;

import com.springsecurity.jwtauth.alldtos.PatientDto;

public interface PatientService {
    PatientDto createPatient(PatientDto patientDto);
    PatientDto getPatientById(Long id);
    List<PatientDto> getAllPatients();
    PatientDto updatePatient(Long id, PatientDto patientDto);
    void deletePatient(Long id);
}