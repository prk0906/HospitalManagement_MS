package com.hospital.patient.repoistory;

import com.hospital.patient.models.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends JpaRepository<Patients,Long> {
}
