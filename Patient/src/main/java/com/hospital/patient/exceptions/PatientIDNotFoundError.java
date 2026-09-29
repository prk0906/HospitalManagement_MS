package com.hospital.patient.exceptions;

public class PatientIDNotFoundError extends Exception{
    public PatientIDNotFoundError(String message) {
        super(message);
    }
}
