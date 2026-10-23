package com.hospital.appointment.Exception;

public class AppoinmentIdNotFound extends RuntimeException{
    public AppoinmentIdNotFound(String message) {
        super(message);
    }
}
