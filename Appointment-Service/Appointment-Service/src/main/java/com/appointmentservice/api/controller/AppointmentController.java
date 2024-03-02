package com.appointmentservice.api.controller;
import com.appointmentservice.api.entities.Appointment;
import com.appointmentservice.api.payload.Doctor;
import com.appointmentservice.api.payload.Patient;
import com.appointmentservice.api.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private RestTemplate restTemplate;

    //http://localhost:8082/appointments
    @PostMapping
    public ResponseEntity<Appointment> createAppointment(@RequestBody Appointment appointment){
        //Invoke patient microservice to check if patient is exists
        ResponseEntity<Patient> patientResponse=restTemplate.getForEntity(
                "http://patient-service/patients/"+appointment.getPatientId(), Patient.class);

        if(patientResponse.getStatusCode() != HttpStatus.OK || patientResponse.getBody()==null){
            //Handle patient not found error
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Patient patient=patientResponse.getBody();
        //Invoke Doctor microservice to check if the doctor exists
        ResponseEntity<Doctor> doctorResponse=restTemplate.getForEntity(
                "http://doctor-service/doctors/"+appointment.getDoctorId(), Doctor.class);

        if (doctorResponse.getStatusCode() !=HttpStatus.OK || doctorResponse.getBody()==null){
            //Handle doctor not found error
     return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        Doctor doctor=doctorResponse.getBody();
        //Save the appointment using the Appointment microservice's Repository
        //appointmentService.saveAppointment(appointment)
         Appointment newAppointment= appointmentService.saveAppointment(appointment);

        //Return the Created Appointment
        return ResponseEntity.status(HttpStatus.CREATED).body(newAppointment);
    }
    @GetMapping
    // Assuming the endpoint for getting all appointments is "/appointments"
    public ResponseEntity<List<Appointment>> getAllAppointments() {
       List<Appointment>  appointment=appointmentService.getAllAppointment();
       return new ResponseEntity<>(appointment,HttpStatus.CREATED);
    }
       /* HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<List<Appointment>> appointmentResponse = restTemplate.exchange(
                "http://appointment-service/appointments/",
                HttpMethod.GET,
                requestEntity,
                new ParameterizedTypeReference<List<Appointment>>() {}
        );

        if (appointmentResponse.getStatusCode() != HttpStatus.OK || appointmentResponse.getBody() == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        List<Appointment> appointments = appointmentResponse.getBody();
        return ResponseEntity.status(HttpStatus.OK).body(appointments);
    }
*/

}
