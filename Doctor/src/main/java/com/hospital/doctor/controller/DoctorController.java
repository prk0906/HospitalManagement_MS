package com.hospital.doctor.controller;

import com.hospital.doctor.dtos.CreateDoctorDTO;
import com.hospital.doctor.dtos.DoctorResponseDTO;
import com.hospital.doctor.service.IDoctorService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
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
}
