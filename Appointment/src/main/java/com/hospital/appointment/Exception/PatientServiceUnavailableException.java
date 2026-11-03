package com.hospital.appointment.Exception;

public class PatientServiceUnavailableException extends RuntimeException {
    public PatientServiceUnavailableException(String s) {
        super(s);
    }
}
