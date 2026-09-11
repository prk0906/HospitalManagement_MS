package com.hospital.patient.dto;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PatientResponseDTO {
	
    private Long patientId;

    private String firstname;

    private String lastname;

    private LocalDate dob;

    private String gender;

    private String phoneNo;

    private String email;
    
    private Long addressid;

    private String houseNo;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;
}
