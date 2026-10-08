package com.hospital.appointment.dtos;

import com.hospital.appointment.entities.AppointmentStatus;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentCreateDTO {
    private Long patientId;

    private Long doctorId;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String reason;

    private String notes;

}
