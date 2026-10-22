package com.hospital.appointment.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

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
