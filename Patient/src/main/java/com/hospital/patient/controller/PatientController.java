package com.hospital.patient.controller;

import com.hospital.patient.dto.PageResponse;
import com.hospital.patient.dto.PatientCreateDTO;
import com.hospital.patient.dto.PatientResponseDTO;
import com.hospital.patient.exceptions.PatientIDNotFoundError;
import com.hospital.patient.service.PatientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/patients")
public class PatientController {
    @Autowired
    private PatientService _patientService;

    @PostMapping
    public ResponseEntity<PatientResponseDTO> createPatient(
            @Valid @RequestBody PatientCreateDTO request){
        PatientResponseDTO response = _patientService.createPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<PatientResponseDTO>> bulkInsertionForPatient(
        @Valid @RequestBody List<PatientCreateDTO> request){
        List<PatientResponseDTO> response = _patientService.bulkInsertionForPatient(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<PatientResponseDTO> getPatientById(@PathVariable("id") Long Id) throws PatientIDNotFoundError {
        PatientResponseDTO response = _patientService.getPatientById(Id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<PatientResponseDTO>> getAllPatients(){
        List<PatientResponseDTO> response = _patientService.getAllPatients();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/pagination")
    public ResponseEntity<PageResponse<PatientResponseDTO>> getAllPatients(@RequestParam(defaultValue = "0") int pageNo,
                                                       @RequestParam(defaultValue = "10") int size,
                                                       @RequestParam(defaultValue = "patientId") String sortBy
                                                        ,@RequestParam(defaultValue = "desc")String direction){
        PageResponse<PatientResponseDTO> response = _patientService.getAllPatients(pageNo, size,sortBy,direction);
        return ResponseEntity.ok(response);
    }
    @PutMapping("{id}")
    public  ResponseEntity<PatientResponseDTO> updatePatient(@PathVariable(name = "id") Long Id,@RequestBody PatientCreateDTO req){
        PatientResponseDTO response = _patientService.updatePatient(Id, req);
        return ResponseEntity.ok(response);
    }
}
