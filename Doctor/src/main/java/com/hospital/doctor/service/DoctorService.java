package com.hospital.doctor.service;

import com.hospital.doctor.builder.DoctorBuilder;
import com.hospital.doctor.dtos.CreateDoctorDTO;
import com.hospital.doctor.dtos.DoctorResponseDTO;
import com.hospital.doctor.dtos.DoctorUpdateDTO;
import com.hospital.doctor.dtos.PageResponse;
import com.hospital.doctor.entities.Doctor;
import com.hospital.doctor.entities.enums.DoctorStatus;
import com.hospital.doctor.exceptions.DoctorIdNotFoundException;
import com.hospital.doctor.repository.IDoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.print.Doc;
import java.util.List;

@Slf4j
@Service
public class DoctorService implements IDoctorService{

    @Autowired
    private IDoctorRepository _repo;

    @Override
    public DoctorResponseDTO createDoctor(CreateDoctorDTO request) {
        log.info("creating new doctor");
        if(request == null) {
            log.warn("Create Doctor request is null");
            throw new IllegalArgumentException("Request is invalid");
        }
        Doctor doctor = DoctorBuilder.createDoctorFromCreateDoctorDTO(request);
        doctor.setDeleted(false);
        Doctor savedDoctor = _repo.save(doctor);
        log.info("Doctor created successfully. Doctor Id:{}",savedDoctor.getDoctorId());
        DoctorResponseDTO response = DoctorBuilder.createDoctorResponseFromDoctor(savedDoctor);
        return response;
    }

    @Override
    public List<DoctorResponseDTO> BulkInsertDoctor(List<CreateDoctorDTO> req) {
        if(req == null || req.isEmpty()){
            log.warn("Create Doctor request is null");
            throw new IllegalArgumentException("Request is invalid");
        }
        List<Doctor> doctorList = req.stream()
                .map(request -> {
                    Doctor doctor = DoctorBuilder.createDoctorFromCreateDoctorDTO(request);
                    doctor.setDeleted(false);
                    return doctor;
                }).toList();
        List<Doctor> doctorsSaved = _repo.saveAll(doctorList);
        List<DoctorResponseDTO> response = doctorsSaved.stream()
                .map(DoctorBuilder::createDoctorResponseFromDoctor).toList();
        return response;
    }

    @Override
    public DoctorResponseDTO GetDoctorById(Long id) throws DoctorIdNotFoundException {
        if(id == null) {
            log.warn("Doctor Id is null");
            throw new IllegalArgumentException("Patient id cannot be null");
        }
        Doctor doctor = _repo.findByDoctorIdAndActiveTrueAndDeletedFalse(id).orElseThrow(() -> new DoctorIdNotFoundException("Doctor with id " + id + " is not found"));
        log.info("Doctor with Id is fetched. Id is {}",doctor.getDoctorId());
        DoctorResponseDTO response = DoctorBuilder.createDoctorResponseFromDoctor(doctor);
        return response;
    }

    @Override
    public List<DoctorResponseDTO> GetAllDoctors() {
        log.info("Request recived in service");
        List<Doctor> doctors = _repo.findAll();
        List<DoctorResponseDTO> response = doctors.stream()
                .filter(doc ->
                    doc.getActive() == true && doc.getDeleted() == false
                )
                .map(DoctorBuilder::createDoctorResponseFromDoctor)
                .toList();
        log.info("Data fetched with size of {}",doctors.size());
        return response;
    }

    @Override
    public PageResponse GetDoctorByPaginationAndSorting(int pageNo, int pageSize,String sortBy,String orderBy) {
        Sort sort;
        if(orderBy.equalsIgnoreCase("desc")) {
             sort = Sort.by(sortBy).descending();
        }else{
            sort = Sort.by(sortBy).ascending();
        }
        Pageable pageable = PageRequest.of(pageNo, pageSize);
        Page<Doctor> page = _repo.findAll(pageable);
        List<DoctorResponseDTO> response = page.getContent().stream()
                .filter(doc -> doc.getActive() == true && doc.getDeleted() == false)
                .map(DoctorBuilder::createDoctorResponseFromDoctor)
                .toList();
        return PageResponse.<DoctorResponseDTO>builder()
                .content(response)
                .pageNo(page.getNumber())
                .pageSize(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public DoctorResponseDTO UpdateDoctor(Long id, DoctorUpdateDTO request) throws DoctorIdNotFoundException {
        if(id == null) {
            log.warn("Doctor Id is null");
            throw new IllegalArgumentException("Patient id cannot be null");
        }
        Doctor doctor = _repo.findByDoctorIdAndActiveTrueAndDeletedFalse(id).orElseThrow(() -> new DoctorIdNotFoundException("Doctor with id " + id + " is not found"));
        Doctor doctor1 = DoctorBuilder.updateDoctorFromDoctorUpdateDTO(doctor, request);
        Doctor save = _repo.save(doctor1);
        return DoctorBuilder.createDoctorResponseFromDoctor(save);
    }

    @Override
    public void DeleteDoctorById(Long id) throws DoctorIdNotFoundException {
        if(id == null) {
            log.warn("Doctor Id is null");
            throw new IllegalArgumentException("Patient id cannot be null");
        }
        Doctor doctor = _repo.findByDoctorIdAndActiveTrueAndDeletedFalse(id).orElseThrow(() -> new DoctorIdNotFoundException("Doctor with id " + id + " is not found"));
        doctor.setActive(false);
        doctor.setDeleted(false);
        _repo.save(doctor);
    }

    @Override
    public List<DoctorResponseDTO> GetDoctorBySpecialization(String specialization) {
        if(specialization.isBlank() || specialization== null)
            throw new IllegalArgumentException("Patient id cannot be null");
        List<Doctor> doctors = _repo.GetDoctorBySpecialization(specialization);
        List<DoctorResponseDTO> response = doctors.stream()
                .map(DoctorBuilder::createDoctorResponseFromDoctor)
                .toList();
        return response;
    }

    @Override
    public List<DoctorResponseDTO> getDoctorByStatus(DoctorStatus status) {
        if( status == null)
            throw new IllegalArgumentException("Patient id cannot be null");
        List<Doctor> doctor = _repo.getDoctorByStatus(status);
        List<DoctorResponseDTO> response = doctor.stream()
                .map(DoctorBuilder::createDoctorResponseFromDoctor)
                .toList();
        return response;
    }
}
