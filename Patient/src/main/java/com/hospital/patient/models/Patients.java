package com.hospital.patient.models;

import jakarta.persistence.Entity;

import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "patients")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patients {
	  	@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long patientId;

	    @Column(nullable = false, length = 100)
	    private String firstname;

	    @Column(nullable = false, length = 100)
	    private String lastname;

	    private LocalDate dob;

	    @Column(length = 20)
	    private String gender;

	    @Column(name = "phone_no", length = 20)
	    private String phoneNo;

	    @Column(unique = true, length = 150)
	    @Email
	    private String email;

	    @OneToOne(
	        cascade = CascadeType.ALL,
	        orphanRemoval = true
	    )
	    @JoinColumn(
	        name = "address_id",
	        referencedColumnName = "addressid"
	    )
	    private Address address;

	    @Column(nullable = false)
	    private Boolean active = true;

	    @Column(nullable = false)
	    private Boolean deleted = false;

	    @Column(name = "created_at", updatable = false)
	    private LocalDateTime createdAt;

	    @Column(name = "updated_at")
	    private LocalDateTime updatedAt;

	    @PrePersist
	    protected void onCreate() {
	        createdAt = LocalDateTime.now();
	        updatedAt = LocalDateTime.now();
	    }

	    @PreUpdate
	    protected void onUpdate() {
	        updatedAt = LocalDateTime.now();
	    }
}
