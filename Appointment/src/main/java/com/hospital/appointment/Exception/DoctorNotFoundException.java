package com.hospital.appointment.Exception;

public class DoctorNotFoundException extends RuntimeException{
    public DoctorNotFoundException(String message) {
        super(message);
    }
}
