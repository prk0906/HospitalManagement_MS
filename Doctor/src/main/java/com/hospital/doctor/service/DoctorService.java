package com.hospital.doctor.service;

import com.hospital.doctor.builder.DoctorBuilder;
import com.hospital.doctor.dtos.CreateDoctorDTO;
import com.hospital.doctor.dtos.DoctorResponseDTO;
import com.hospital.doctor.entities.Doctor;
import com.hospital.doctor.repository.IDoctorRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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
        if(req == null){
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
                .map(doctor -> {
                    return DoctorBuilder.createDoctorResponseFromDoctor(doctor);
                }).toList();
        return response;
    }
}
