package com.hospital.appointment.Exception;

import com.hospital.appointment.dtos.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalException {

    @ExceptionHandler(PatientNotFoundException.class)
    public ResponseEntity<ErrorResponse> patientNotFound(PatientNotFoundException ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(DoctorNotFoundException.class)
    public ResponseEntity<ErrorResponse> doctorNotFound(DoctorNotFoundException ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(AppoinmentIdNotFound.class)
    public ResponseEntity<ErrorResponse> appointmentNotFounr(AppoinmentIdNotFound ex){
        ErrorResponse error = new ErrorResponse(HttpStatus.NOT_FOUND.value(),ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
