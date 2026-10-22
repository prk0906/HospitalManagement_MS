package com.hospital.appointment.Exception;

public class PatientNotFoundException extends RuntimeException{
    public PatientNotFoundException(String message) {
        super(message);
    }
}
