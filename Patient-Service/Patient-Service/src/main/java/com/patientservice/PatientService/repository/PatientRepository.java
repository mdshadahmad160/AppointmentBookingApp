package com.patientservice.PatientService.repository;
import com.patientservice.PatientService.entities.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository  extends JpaRepository<Patient,Long> {
}
