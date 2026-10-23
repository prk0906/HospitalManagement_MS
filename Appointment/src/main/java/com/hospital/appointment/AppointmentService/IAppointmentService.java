package com.hospital.appointment.AppointmentService;

import com.hospital.appointment.dtos.AppointmentCreateDTO;
import com.hospital.appointment.dtos.AppointmentResponseDTO;

import java.util.List;

public interface IAppointmentService {
    AppointmentResponseDTO createAppointment(AppointmentCreateDTO request);
    List<AppointmentResponseDTO> createBulkAppointment(List<AppointmentCreateDTO> request);

    AppointmentResponseDTO getAppointmentById(Long id);
}
