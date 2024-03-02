package com.patientservice.PatientService.service;
import com.patientservice.PatientService.entities.Patient;
import com.patientservice.PatientService.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PatientService {
    @Autowired
  private PatientRepository patientRepository;
    public Patient savePatient(Patient patient){
        return patientRepository.save(patient);
    }
    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }
    public Patient getPatientById(Long id){
        return patientRepository.findById(id).orElseThrow(null);
    }
    public void  deletePatient(Long id){
        patientRepository.deleteById(id);
    }
}
