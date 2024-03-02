package com.doctorservice.api.repository;
import com.doctorservice.api.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;



public interface DoctorRepository extends JpaRepository<Doctor,Long> {
}
