package com.hospital.appointment.Clients;

import com.hospital.appointment.dtos.DoctorResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "Doctor-Service",
        url = "${doctor.service.url}"
)
public interface DoctorClient {
    @GetMapping("/doctor/{id}")
    DoctorResponseDTO getDoctorById(@PathVariable("id") Long Id);
}
