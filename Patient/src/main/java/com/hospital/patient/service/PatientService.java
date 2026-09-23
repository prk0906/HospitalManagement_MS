package com.hospital.patient.service;

import com.hospital.patient.dto.PatientCreateDTO;
import com.hospital.patient.dto.PatientResponseDTO;
import jakarta.validation.Valid;

public interface PatientService {
    PatientResponseDTO createPatient(@Valid PatientCreateDTO request);
}
