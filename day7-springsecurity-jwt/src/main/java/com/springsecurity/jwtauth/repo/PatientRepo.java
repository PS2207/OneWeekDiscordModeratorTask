package com.springsecurity.jwtauth.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springsecurity.jwtauth.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {

}
