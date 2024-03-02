package com.appointmentservice.api.service;
import com.appointmentservice.api.entities.Appointment;
import com.appointmentservice.api.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    private AppointmentRepository appointmentRepository;


    public Appointment saveAppointment(Appointment appointment){
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getAllAppointment(){
        return appointmentRepository.findAll();
    }
    public Appointment getAppointmentById(Long id){
        return appointmentRepository.findById(id).orElseThrow(null);
    }
    public void deleteAppointment(Long id){
        appointmentRepository.deleteById(id);
    }
}
