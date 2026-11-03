package com.hospital.appointment.Clients;

import com.hospital.appointment.Exception.PatientNotFoundException;
import com.hospital.appointment.Exception.PatientServiceUnavailableException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;

@Component
public class PatientClientFallbackFactory implements FallbackFactory<PatientClient> {
    @Override
    public PatientClient create(Throwable cause) {
        return id->{
            if(cause instanceof FeignException.NotFound){
                throw new PatientNotFoundException(
                        "Patient with ID " + id + " is not found"
                );
            }
            throw new PatientServiceUnavailableException(
                    "Patient Service is currently unavailable. Please try again later."
            );
        };
    }
}
