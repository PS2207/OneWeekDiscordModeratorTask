package com.springsecurity.jwtauth.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.springsecurity.jwtauth.alldtos.PatientDto;
import com.springsecurity.jwtauth.entity.Patient;
import com.springsecurity.jwtauth.repo.PatientRepo;
import com.springsecurity.jwtauth.security.AuthService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService{
    private final PatientRepo patientRepo;
    private final ModelMapper modelMapper;

    @Override
    public PatientDto createPatient(PatientDto patientDto) {
        Patient patient = modelMapper.map(patientDto, Patient.class);
        Patient saved = patientRepo.save(patient);
        return modelMapper.map(saved, PatientDto.class);
    }

    @Override
    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        return modelMapper.map(patient, PatientDto.class);
    }

    @Override
    public List<PatientDto> getAllPatients() {
        return patientRepo.findAll()
                .stream()
                .map(patient -> modelMapper.map(patient, PatientDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDto updatePatient(Long id, PatientDto patientDto) {
        Patient patient = patientRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setName(patientDto.getName());
        patient.setBirthDate(patientDto.getBirthDate());
        patient.setGender(patientDto.getGender());
        patient.setBloodGroup(patientDto.getBloodGroup());
        return modelMapper.map(patientRepo.save(patient), PatientDto.class);
    }

    @Override
    public void deletePatient(Long id) {
        patientRepo.deleteById(id);
    }
}
