package com.hospital.appointment.Clients;

import com.hospital.appointment.Exception.PatientNotFoundException;
import com.hospital.appointment.Exception.PatientServiceUnavailableException;
import feign.FeignException;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Component
public class DoctorClientFallbackFactory implements FallbackFactory<DoctorClient> {
    @Override
    public DoctorClient create(Throwable cause) {
        return id->{
            if(cause instanceof FeignException.NotFound){
                throw new PatientNotFoundException(
                        "Doctor with ID " + id + " is not found"
                );
            }
            throw new PatientServiceUnavailableException(
                    "Doctor Service is currently unavailable. Please try again later."
            );
        };
    }
}
