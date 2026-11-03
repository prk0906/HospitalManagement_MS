package com.hospital.appointment.Clients;

import com.hospital.appointment.dtos.PatientResponseDTO;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "Patient-Service",
        fallbackFactory=PatientClientFallbackFactory.class)
public interface PatientClient {
    @GetMapping("/patients/{id}")
    PatientResponseDTO getPatientById(@PathVariable("id") Long Id);
}
