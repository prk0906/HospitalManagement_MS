package com.hospital.doctor.repository;

import com.hospital.doctor.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.Optional;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByDoctorIdAndActiveTrueAndDeletedFalse(Long id);
}
