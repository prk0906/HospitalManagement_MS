package com.hospital.appointment.Controller;

import com.hospital.appointment.AppointmentService.IAppointmentService;
import com.hospital.appointment.dtos.AppointmentCreateDTO;
import com.hospital.appointment.dtos.AppointmentResponseDTO;
import com.hospital.appointment.dtos.AppointmentUpdateDTO;
import com.hospital.appointment.dtos.PageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    @Autowired
    private IAppointmentService _service;
    @PostMapping
    public ResponseEntity<AppointmentResponseDTO> createAppointment(@RequestBody AppointmentCreateDTO request){
        AppointmentResponseDTO response = _service.createAppointment(request);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/bulk")
    public ResponseEntity<List<AppointmentResponseDTO>> createBulkAppointment(@RequestBody List<AppointmentCreateDTO> request){
        List<AppointmentResponseDTO> response = _service.createBulkAppointment(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> getAppointmentById(@PathVariable(name = "id") Long Id){
        AppointmentResponseDTO response = _service.getAppointmentById(Id);
        return ResponseEntity.ok(response);
    }
    @GetMapping
    public ResponseEntity<List<AppointmentResponseDTO>> getAppointments(){
        List<AppointmentResponseDTO> response = _service.getAppointments();
        return ResponseEntity.ok(response);
    }
    @GetMapping("/Pagination")
    public ResponseEntity<PageResponse<AppointmentResponseDTO>> getAppointmentsByPagination(@RequestParam(defaultValue = "0")int pageIndex,
                                                                                                 @RequestParam(defaultValue = "10") int pageSize){
        PageResponse<AppointmentResponseDTO> response = _service.getAppointmentsByPagination(pageIndex, pageSize);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/{id}")
    public ResponseEntity<AppointmentResponseDTO> updateAppointment(@PathVariable(name="id")Long id, @RequestBody AppointmentUpdateDTO request){
        AppointmentResponseDTO responseDTO = _service.updateAppointment(id, request);
        return ResponseEntity.ok(responseDTO);
    }
}
