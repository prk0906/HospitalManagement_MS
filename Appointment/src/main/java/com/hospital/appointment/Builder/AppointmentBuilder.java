package com.hospital.appointment.Builder;

import com.hospital.appointment.dtos.AppointmentCreateDTO;
import com.hospital.appointment.dtos.AppointmentResponseDTO;
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
}
