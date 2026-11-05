package com.hospital.appointment.Exception;

public class DoctorServiceUnavailableException extends RuntimeException{
    public DoctorServiceUnavailableException(String message) {
        super(message);
    }
}
