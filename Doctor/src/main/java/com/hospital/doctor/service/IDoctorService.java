package com.hospital.doctor.service;

import com.hospital.doctor.dtos.CreateDoctorDTO;
import com.hospital.doctor.dtos.DoctorResponseDTO;

import java.util.List;

public interface IDoctorService {
    DoctorResponseDTO createDoctor(CreateDoctorDTO request);

    List<DoctorResponseDTO> BulkInsertDoctor(List<CreateDoctorDTO> req);
}
