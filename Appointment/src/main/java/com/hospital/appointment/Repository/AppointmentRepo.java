package com.hospital.appointment.Repository;

import com.hospital.appointment.dtos.AppointmentResponseDTO;
import com.hospital.appointment.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findByAppointmentIdAndActiveTrueAndDeletedFalse(Long id);
}
