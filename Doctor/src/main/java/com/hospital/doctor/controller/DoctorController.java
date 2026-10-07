package com.hospital.doctor.controller;

import com.hospital.doctor.dtos.CreateDoctorDTO;
import com.hospital.doctor.dtos.DoctorResponseDTO;
import com.hospital.doctor.dtos.DoctorUpdateDTO;
import com.hospital.doctor.dtos.PageResponse;
import com.hospital.doctor.exceptions.DoctorIdNotFoundException;
import com.hospital.doctor.service.IDoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/doctor")
@Slf4j
public class DoctorController {

    @Autowired
    private IDoctorService _service;

    @PostMapping
    public ResponseEntity<DoctorResponseDTO> createDoctor(@RequestBody CreateDoctorDTO request){
        log.info("Post /doctors - create doctor request received");
        DoctorResponseDTO response = _service.createDoctor(request);
        log.info("POST /doctor - doctor created successfully. Id: {}",response.getDoctorId());
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @PostMapping("/bulk")
    public ResponseEntity<List<DoctorResponseDTO>> BulkInsertDoctor(@RequestBody List<CreateDoctorDTO> req){
        log.info("Post /doctors - create doctor request received");
        List<DoctorResponseDTO> response = _service.BulkInsertDoctor(req);
        log.info("POST /doctor - doctor created successfully.");
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> GetDoctorById(@PathVariable(name = "id") Long id) throws DoctorIdNotFoundException {
        log.info("Get /id - get doctor request received");
        DoctorResponseDTO response = _service.GetDoctorById(id);
        log.info("Get /id - doctor data fetched successfully. id {}",response.getDoctorId());
        return ResponseEntity.ok(response);
    }

    @GetMapping
    public ResponseEntity<List<DoctorResponseDTO>> GetAllDoctors(){
        log.info("Get /id - get doctor request received");
        List<DoctorResponseDTO> response = _service.GetAllDoctors();
        log.info("Get /id - doctor data fetched successfully.");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/pagination")
    public ResponseEntity<PageResponse> GetDoctorByPaginationAndSorting(@RequestParam(defaultValue = "0") int pageNo,
                                                                        @RequestParam(defaultValue = "20")int pageSize,
                                                                        @RequestParam(defaultValue = "doctorId")String sortBy,
                                                                        @RequestParam(defaultValue = "asc") String orderBy){
        PageResponse pageResponse = _service.GetDoctorByPaginationAndSorting(pageNo, pageSize,sortBy,orderBy);
        return ResponseEntity.ok(pageResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DoctorResponseDTO> UpdateDoctor(@PathVariable(name = "id")Long id, @RequestBody DoctorUpdateDTO request) throws DoctorIdNotFoundException {
        DoctorResponseDTO response = _service.UpdateDoctor(id,request);
        return ResponseEntity.ok(response);
    }
}
