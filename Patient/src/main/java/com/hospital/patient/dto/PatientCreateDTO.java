package com.hospital.patient.dto;

import java.time.LocalDate;

import com.hospital.patient.models.Address;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class PatientCreateDTO {
	
		@NotBlank(message = "First name is requried")
		@Size(min=2,max=50,message = "First name must be between 2 and 50 character")
	    private String firstname;
		
		@NotBlank(message = "Last name is requried")
		@Size(min=2,max=50,message = "Last name must be between 2 and 50 characters")
	    private String lastname;
		
		@NotNull(message = "Data of birth is requried")
	    private LocalDate dob;
		
		@NotBlank(message = "Gender is requried")
	    private String gender;

		@NotNull(message = "Phone number is requried")
	    private String phoneNo;
		
		@NotNull(message = "Email is requried")
	    private String email;

		@NotNull(message = "Address is requried")
		@Valid
	    private AddressCreateDTO addressDto;
}
