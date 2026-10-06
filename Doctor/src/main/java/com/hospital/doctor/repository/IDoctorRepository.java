package com.hospital.doctor.repository;

import com.hospital.doctor.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor,Long> {
}
