package com.hospital.patient.builders;

import com.hospital.patient.dto.PatientCreateDTO;
import com.hospital.patient.dto.PatientResponseDTO;
import com.hospital.patient.dto.PatientUpdateDTO;
import com.hospital.patient.models.Patients;

import java.time.LocalDate;

public class PatientBuilder {
    public static Patients createPatientFromPatientCreateDto(PatientCreateDTO request) {
        return Patients.builder()
                .firstname(request.getFirstname())
                .lastname(request.getLastname())
                .dob(request.getDob())
                .gender(request.getGender())
                .phoneNo(request.getPhoneNo())
                .email(request.getEmail())
                .active(true)
                .deleted(false)
                .build();
    }

    public static PatientResponseDTO createPatientResponseFromPatient(Patients req) {
        return PatientResponseDTO.builder()
                .patientId(req.getPatientId())
                .firstname(req.getFirstname())
                .lastname(req.getLastname())
                .dob(req.getDob())
                .gender(req.getGender())
                .phoneNo(req.getPhoneNo())
                .email(req.getEmail())
                .addressid(req.getAddress().getAddressid())
                .houseNo(req.getAddress().getHouseNo())
                .street(req.getAddress().getStreet())
                .city(req.getAddress().getCity())
                .state(req.getAddress().getState())
                .country(req.getAddress().getCountry())
                .zipCode(req.getAddress().getZipCode())
                .build();
    }

    public static Patients createPatientsFromPatientUpdateDTO(Patients patient, PatientUpdateDTO req){
        patient.setFirstname(req.getFirstname());
        patient.setLastname(req.getLastname());
        patient.setDob(req.getDob());
        patient.setGender(req.getGender());
        patient.setPhoneNo(req.getPhoneNo());
        patient.setEmail(req.getEmail());
        patient.setActive(req.getActive());
        return patient;
    }
}
