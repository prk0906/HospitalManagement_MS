package com.hospital.patient.dto;

import java.time.LocalDate;

import com.hospital.patient.models.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientCreateDTO {
	
		@NotBlank
	    private String firstname;

	    private String lastname;

	    private LocalDate dob;

	    private String gender;

	    private String phoneNo;

	    private String email;

	    private AddressCreateDTO addressDto;
}
