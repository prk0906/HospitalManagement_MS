package com.hospital.patient.service;

import com.hospital.patient.dto.PageResponse;
import com.hospital.patient.dto.PatientCreateDTO;
import com.hospital.patient.dto.PatientResponseDTO;
import com.hospital.patient.dto.PatientUpdateDTO;
import com.hospital.patient.exceptions.PatientIDNotFoundError;
import jakarta.validation.Valid;

import java.util.*;

public interface PatientService {
    PatientResponseDTO createPatient(@Valid PatientCreateDTO request);

    List<PatientResponseDTO> bulkInsertionForPatient(@Valid List<PatientCreateDTO> request);

    PatientResponseDTO getPatientById(Long Id) throws PatientIDNotFoundError;

    List<PatientResponseDTO> getAllPatients();
    PageResponse<PatientResponseDTO> getAllPatients(int pageNo, int size,String sortBy,String direction);

    PatientResponseDTO updatePatient(Long id, PatientUpdateDTO req) throws PatientIDNotFoundError;
}
