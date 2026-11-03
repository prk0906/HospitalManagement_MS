package com.hospital.appointment.AppointmentService;

import com.hospital.appointment.dtos.AppointmentCreateDTO;
import com.hospital.appointment.dtos.AppointmentResponseDTO;
import com.hospital.appointment.dtos.AppointmentUpdateDTO;
import com.hospital.appointment.dtos.PageResponse;

import java.time.LocalDate;
import java.util.List;

public interface IAppointmentService {
    AppointmentResponseDTO createAppointment(AppointmentCreateDTO request);
    List<AppointmentResponseDTO> createBulkAppointment(List<AppointmentCreateDTO> request);

    AppointmentResponseDTO getAppointmentById(Long id);

    List<AppointmentResponseDTO> getAppointments();

    PageResponse<AppointmentResponseDTO>  getAppointmentsByPagination(int pageIndex, int pageSize);

    AppointmentResponseDTO updateAppointment(Long id, AppointmentUpdateDTO request);

    void deleteAppointment(Long id);

    List<AppointmentResponseDTO> getPatientHistory(Long id);

    List<AppointmentResponseDTO> getDoctorAppointments(Long id);

    List<AppointmentResponseDTO> getAppointmentsByDate(LocalDate date);

    AppointmentResponseDTO updateAppointmentStatusToConfrim(Long id,String status);
    AppointmentResponseDTO CancelAppointment(Long id);
    AppointmentResponseDTO CompleteAppointment(Long id);
}
