package com.doctorservice.api.service;
import com.doctorservice.api.entities.Doctor;
import com.doctorservice.api.repository.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoctorService {
    @Autowired
    private DoctorRepository doctorRepository;

    public Doctor saveDoctor(Doctor doctor){
        return doctorRepository.save(doctor);
    }
    public List<Doctor> getAllDoctors(){
        return doctorRepository.findAll();
    }
    public Doctor getDoctorById(Long id){
        return doctorRepository.findById(id).orElseThrow(null );
    }
    public void deleteDoctor(Long id){
        doctorRepository.deleteById(id);
    }
}
