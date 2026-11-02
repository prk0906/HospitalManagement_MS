package com.hospital.appointment.Repository;

import com.hospital.appointment.dtos.AppointmentResponseDTO;
import com.hospital.appointment.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.swing.text.html.Option;
import java.util.*;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
    Optional<Appointment> findByAppointmentIdAndActiveTrueAndDeletedFalse(Long id);

    Optional<List<Appointment>> findByPatientId(Long id);

    Optional<List<Appointment>> findByDoctorId(Long id);
}
