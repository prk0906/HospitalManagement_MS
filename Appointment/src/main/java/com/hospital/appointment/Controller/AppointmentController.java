package com.hospital.appointment.Controller;

import com.hospital.appointment.AppointmentService.IAppointmentService;
import com.hospital.appointment.dtos.AppointmentCreateDTO;
import com.hospital.appointment.dtos.AppointmentResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
