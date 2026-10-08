package com.hospital.doctor.repository;

import com.hospital.doctor.dtos.DoctorResponseDTO;
import com.hospital.doctor.entities.Doctor;
import com.hospital.doctor.entities.enums.DoctorStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.*;

@Repository
public interface IDoctorRepository extends JpaRepository<Doctor,Long> {
    Optional<Doctor> findByDoctorIdAndActiveTrueAndDeletedFalse(Long id);

    @Query("""
            Select d
            FROM Doctor d
            WHERE d.active = true AND d.deleted = false
                       AND d.specialization LIKE CONCAT('%',:specialization,'%')
           """)
    List<Doctor> GetDoctorBySpecialization(@Param("specialization") String specialization);

    @Query("""
            Select d
            FROM Doctor d
            WHERE d.active = true AND d.deleted = false
                       AND d.status = :status
           """)
    List<Doctor> getDoctorByStatus(@Param("status") DoctorStatus status);
}
