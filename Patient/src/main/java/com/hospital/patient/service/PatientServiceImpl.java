package com.hospital.patient.service;

import com.hospital.patient.builders.AddressBuilder;
import com.hospital.patient.builders.PatientBuilder;
import com.hospital.patient.dto.PatientCreateDTO;
import com.hospital.patient.dto.PatientResponseDTO;
import com.hospital.patient.models.Address;
import com.hospital.patient.models.Patients;
import com.hospital.patient.repoistory.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService{

    @Autowired
    private PatientRepository _repo;

    @Override
    public PatientResponseDTO createPatient(PatientCreateDTO request) {
        if(request==null)
            throw new IllegalArgumentException("Patient Request Cannot be null");
        Address address = AddressBuilder.createAddressFromAddressCreateDto(request.getAddressDto());
        Patients patients = PatientBuilder.createPatientFromPatientCreateDto(request);
        patients.setAddress(address);
        Patients save = _repo.save(patients);
        PatientResponseDTO response = PatientBuilder.createPatientResponseFromPatient(save);

        return response;
    }
}
