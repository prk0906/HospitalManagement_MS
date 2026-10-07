package com.hospital.doctor.service;

import com.hospital.doctor.dtos.CreateDoctorDTO;
import com.hospital.doctor.dtos.DoctorResponseDTO;
import com.hospital.doctor.dtos.DoctorUpdateDTO;
import com.hospital.doctor.dtos.PageResponse;
import com.hospital.doctor.exceptions.DoctorIdNotFoundException;

import java.util.List;

public interface IDoctorService {
    DoctorResponseDTO createDoctor(CreateDoctorDTO request);

    List<DoctorResponseDTO> BulkInsertDoctor(List<CreateDoctorDTO> req);

    DoctorResponseDTO GetDoctorById(Long id) throws DoctorIdNotFoundException;

    List<DoctorResponseDTO> GetAllDoctors();

    PageResponse GetDoctorByPaginationAndSorting(int pageNo, int pageSize,String sortBy,String orderBy);

    DoctorResponseDTO UpdateDoctor(Long id , DoctorUpdateDTO request) throws DoctorIdNotFoundException;
}
