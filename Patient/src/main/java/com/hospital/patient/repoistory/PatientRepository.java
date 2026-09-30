package com.hospital.patient.repoistory;

import com.hospital.patient.dto.PatientResponseDTO;
import com.hospital.patient.models.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patients,Long> {
    Optional<Patients> findByPatientIdAndActiveTrueAndDeletedFalse(Long id);
}
