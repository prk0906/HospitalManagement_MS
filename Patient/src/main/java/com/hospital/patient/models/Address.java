package com.hospital.patient.models;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "address")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
	 	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long addressid;

	    @Column(name = "house_no", length = 50)
	    private String houseNo;

	    @Column(length = 150)
	    private String street;

	    @Column(length = 100)
	    private String city;

	    @Column(length = 100)
	    private String state;

	    @Column(length = 100)
	    private String country;

	    @Column(name = "zip_code", length = 20)
	    private String zipCode;

	    @Column(nullable = false)
	    private Boolean active = true;

	    @Column(nullable = false)
	    private Boolean deleted = false;

	    @OneToOne(mappedBy = "address")
	    private Patients patients;
}
