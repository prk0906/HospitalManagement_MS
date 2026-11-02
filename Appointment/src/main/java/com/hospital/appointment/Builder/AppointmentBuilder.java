package com.hospital.appointment.Builder;

import com.hospital.appointment.dtos.AppointmentCreateDTO;
import com.hospital.appointment.dtos.AppointmentResponseDTO;
import com.hospital.appointment.dtos.AppointmentUpdateDTO;
import com.hospital.appointment.entities.Appointment;

public class AppointmentBuilder {
    public static Appointment createAppointmentFromAppointmentCreateDto(AppointmentCreateDTO request){
        return Appointment.builder()
                .patientId(request.getPatientId())
                .doctorId(request.getDoctorId())
                .appointmentDate(request.getAppointmentDate())
                .appointmentTime(request.getAppointmentTime())
                .reason(request.getReason())
                .notes(request.getNotes())
                .build();
    }

    public static AppointmentResponseDTO createAppointmentResponseFromAppointment(Appointment appointment){
        return AppointmentResponseDTO.builder()
                .appointmentId(appointment.getAppointmentId())
                .patientId(appointment.getPatientId())
                .doctorId(appointment.getDoctorId())
                .appointmentDate(appointment.getAppointmentDate())
                .appointmentTime(appointment.getAppointmentTime())
                .reason(appointment.getReason())
                .notes(appointment.getNotes())
                .status(appointment.getStatus())
                .active(appointment.getActive())
                .deleted(appointment.getDeleted())
                .build();
    }
    public static Appointment updateAppointmentFromDTO(
            Appointment appointment,
            AppointmentUpdateDTO request) {

        appointment.setPatientId(request.getPatientId());
        appointment.setDoctorId(request.getDoctorId());
        appointment.setAppointmentDate(request.getAppointmentDate());
        appointment.setAppointmentTime(request.getAppointmentTime());
        appointment.setReason(request.getReason());
        appointment.setStatus(request.getStatus());
        appointment.setNotes(request.getNotes());
        appointment.setActive(request.getActive());

        return appointment;
    }
}
