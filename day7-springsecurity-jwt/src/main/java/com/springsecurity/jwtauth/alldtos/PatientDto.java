package com.springsecurity.jwtauth.alldtos;
import lombok.Data;

import java.time.LocalDate;

//Patient DTO (good practice to separate API from entity)
@Data
public class PatientDto {
    private Long id;
    private String name;
    private LocalDate birthDate;
    private String gender;
    private String bloodGroup;
}