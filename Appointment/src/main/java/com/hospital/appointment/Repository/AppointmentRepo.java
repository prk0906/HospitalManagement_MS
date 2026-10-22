package com.hospital.appointment.Repository;

import com.hospital.appointment.entities.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepo extends JpaRepository<Appointment,Long> {
}
