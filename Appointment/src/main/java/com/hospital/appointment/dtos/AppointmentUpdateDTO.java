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
public class AppointmentUpdateDTO {
    private Long patientId;

    private Long doctorId;

    private LocalDate appointmentDate;

    private LocalTime appointmentTime;

    private String reason;

    private AppointmentStatus status;

    private String notes;

    private Boolean active;
}
