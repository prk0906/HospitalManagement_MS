package com.hospital.patient.service;

import com.hospital.patient.builders.AddressBuilder;
import com.hospital.patient.builders.PatientBuilder;
import com.hospital.patient.dto.PageResponse;
import com.hospital.patient.dto.PatientCreateDTO;
import com.hospital.patient.dto.PatientResponseDTO;
import com.hospital.patient.exceptions.PatientIDNotFoundError;
import com.hospital.patient.models.Address;
import com.hospital.patient.models.Patients;
import com.hospital.patient.repoistory.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    @Override
    public List<PatientResponseDTO> bulkInsertionForPatient(List<PatientCreateDTO> request) {
        if(request == null)
            throw  new IllegalArgumentException("Patient Request Cannot be null");
        List<Patients> list = request.stream()
                .map(req -> {
                    Address address = AddressBuilder.createAddressFromAddressCreateDto(req.getAddressDto());
                    Patients patient = PatientBuilder.createPatientFromPatientCreateDto(req);
                    patient.setAddress(address);
                    return patient;
                }).toList();
        List<Patients> patients = _repo.saveAll(list);
        List<PatientResponseDTO> response = patients.stream()
                .map(PatientBuilder::createPatientResponseFromPatient).toList();
        return response;
    }

    @Override
    public PatientResponseDTO getPatientById(Long Id) throws PatientIDNotFoundError {
        if(Id == null)
            throw new IllegalArgumentException("Patient Id is empyt. Id is mandatory");
        Patients patientResponse = _repo.findById(Id).orElseThrow(() -> new PatientIDNotFoundError("Patient With Id " + Id + " is not found"));
        return PatientBuilder.createPatientResponseFromPatient(patientResponse);
    }

    @Override
    public List<PatientResponseDTO> getAllPatients() {
        List<Patients> patients = _repo.findAll();
        List<PatientResponseDTO> response = patients.stream()
                .map(PatientBuilder::createPatientResponseFromPatient)
                .toList();
        return response;
    }

    @Override
    public PageResponse<PatientResponseDTO> getAllPatients(int pageNo, int size,String sortBy,String direction) {
       Sort sort;
       if(direction.equalsIgnoreCase("desc"))
           sort = Sort.by(sortBy).descending();
       else
           sort = Sort.by(sortBy).ascending();

        Pageable page  = PageRequest.of(pageNo, size, sort);
        Page<Patients> pages = _repo.findAll(page);
        List<PatientResponseDTO> response = pages.getContent()
                .stream()
                .map(PatientBuilder::createPatientResponseFromPatient)
                .toList();
        return PageResponse.<PatientResponseDTO>builder()
                .content(response)
                .pageNo(pages.getNumber())
                .pageSize(pages.getSize())
                .totalElements(pages.getTotalElements())
                .totalPages(pages.getTotalPages())
                .last(pages.isLast())
                .build();
    }

    @Override
    public PatientResponseDTO updatePatient(Long id, PatientCreateDTO req) {
        return null;
    }
}
