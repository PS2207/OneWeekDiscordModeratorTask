package com.springsecurity.jwtauth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springsecurity.jwtauth.alldtos.PatientDto;
import com.springsecurity.jwtauth.entity.Patient;
import com.springsecurity.jwtauth.services.PatientService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class PatientController {

	 private final PatientService patientService;

	    @PostMapping
	    public ResponseEntity<PatientDto> createPatient(@RequestBody PatientDto patientDto) {
	        return ResponseEntity.ok(patientService.createPatient(patientDto));
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<PatientDto> getPatientById(@PathVariable Long id) {
	        return ResponseEntity.ok(patientService.getPatientById(id));
	    }

	    @GetMapping
	    public ResponseEntity<List<PatientDto>> getAllPatients() {
	        return ResponseEntity.ok(patientService.getAllPatients());
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<PatientDto> updatePatient(@PathVariable Long id, @RequestBody PatientDto patientDto) {
	        return ResponseEntity.ok(patientService.updatePatient(id, patientDto));
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
	        patientService.deletePatient(id);
	        return ResponseEntity.ok("Patient deleted successfully");
	    }
}


//Patient APIs will look like this:
//Create Patient → POST /api/v1/admin
//Get Patient by Id → GET /api/v1/admin/{id}
//Get All Patients → GET /api/v1/admin
//Update Patient → PUT /api/v1/admin/{id}
//Delete Patient → DELETE /api/v1/admin/{id}







































