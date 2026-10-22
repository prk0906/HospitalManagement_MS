package com.hospital.appointment.AppointmentService;

import com.hospital.appointment.dtos.AppointmentCreateDTO;
import com.hospital.appointment.dtos.AppointmentResponseDTO;

public interface IAppointmentService {
    AppointmentResponseDTO createAppointment(AppointmentCreateDTO request);
}
