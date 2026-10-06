package com.hospital.doctor.builder;

import com.hospital.doctor.dtos.CreateDoctorDTO;
import com.hospital.doctor.dtos.DoctorResponseDTO;
import com.hospital.doctor.entities.Doctor;

public class DoctorBuilder {
    public static Doctor createDoctorFromCreateDoctorDTO(CreateDoctorDTO req){
        return Doctor.builder()
                .name(req.getName())
                .specialization(req.getSpecialization())
                .phone(req.getPhone())
                .email(req.getEmail())
                .status(req.getStatus())
                .active(req.getActive())
                .build();
    }

    public static DoctorResponseDTO createDoctorResponseFromDoctor(Doctor doctor){
        return DoctorResponseDTO.builder()
                .doctorId(doctor.getDoctorId())
                .name(doctor.getName())
                .email(doctor.getEmail())
                .specialization(doctor.getSpecialization())
                .phone(doctor.getPhone())
                .status(doctor.getStatus())
                .active(doctor.getActive())
                .deleted(doctor.getDeleted())
                .createdAt(doctor.getCreatedAt())
                .updatedAt(doctor.getUpdatedAt())
                .build();
    }
}
