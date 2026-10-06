package com.hospital.patient.repoistory;

import com.hospital.patient.dto.PatientResponseDTO;
import com.hospital.patient.models.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients,Long> {
    Optional<Patients> findByPatientIdAndActiveTrueAndDeletedFalse(Long id);

    @Query("""
            Select p
            FROM Patients p
            Where p.active = true
                AND p.deleted = false
                AND(
                    LOWER(p.firstname) LIKE LOWER(CONCAT('%',:name,'%'))
                    OR LOWER(p.lastname) LIKE LOWER(CONCAT('%',:name,'%'))
                    )
    """)
    List<Patients> searchPatientByName(@Param("name") String name);

    @Query("""
        Update Patients p
        SET p.active = :active
        WHERE p.patientId = :id
        AND p.deleted = false
    """)
    @Modifying
    @Transactional
    int activeOrDeactivePatient(@Param("id")Long id,@Param("active") boolean active);
}
