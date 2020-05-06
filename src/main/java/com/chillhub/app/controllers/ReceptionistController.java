package com.chillhub.app.controllers;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chillhub.app.custom_exceptions.CustomApiException;
import com.chillhub.app.entities.Appointment;
import com.chillhub.app.entities.Patient;
import com.chillhub.app.entities.Receptionist;
import com.chillhub.app.services.IAppointmentService;
import com.chillhub.app.services.IPatientService;
import com.chillhub.app.services.IReceptionistService;

@RestController
@CrossOrigin
@RequestMapping("api/receptionist")
@PreAuthorize("hasRole('Receptionist')")
public class ReceptionistController {
	
	@Autowired
	IReceptionistService receptionistService;
	
	@Autowired
	IPatientService patientService;
	
	@Autowired
	IAppointmentService appointmentService;
	
	@GetMapping("/{id}")
	public Receptionist getReceptionistById(@PathVariable int id) throws CustomApiException{
		if(!receptionistService.getOneById(id).isPresent()) {
			throw new CustomApiException("receptionist with id " + id + " is not found !!!");
		}
		return receptionistService.getOneById(id).get();
	}
	
	@GetMapping("/patients")
	public List<Patient> getAllPatientsList() {
		return patientService.getAll();
	}
	
	@GetMapping("/patients/{id}")
	public Patient getPatientById(@PathVariable int id) throws CustomApiException {
		if(!patientService.getOneById(id).isPresent()) {
			throw new CustomApiException("patient with id " + id + " is not found !!!");
		}
		return patientService.getOneById(id).get();
	}
	
	@PostMapping("/patients")
	public void addPatient(@RequestBody Patient patient) {
		patientService.addOrUpdate(patient);
	}
	
	@PatchMapping("/patients/{id}")
	public void updatePatient(@PathVariable int id, @RequestBody Patient patient) throws CustomApiException {
		if(!patientService.getOneById(id).isPresent()) {
			throw new CustomApiException("patient with id " + id + " is not found !!!");
		}
		patient.setId(id);
		patientService.addOrUpdate(patient);
	}
	
	@DeleteMapping("/patients/{id}")
	public void deletePatient(@PathVariable int id) throws CustomApiException {
		if(!patientService.getOneById(id).isPresent()) {
			throw new CustomApiException("patient with id " + id + " is not found !!!");
		}
		patientService.delete(patientService.getOneById(id).get());
	}
	
	@PostMapping("/{rid}/patients/{pid}/appointments")
	public void addAppointmentToPatient(@PathVariable("pid") int patientId, @PathVariable("rid") int receptionistId, @RequestBody Appointment appointment) throws CustomApiException {
		if(!receptionistService.getOneById(receptionistId).isPresent()) {
			throw new CustomApiException("receptionist with id " + receptionistId + " is not found !!!");
		}
		if(!patientService.getOneById(patientId).isPresent()) {
			throw new CustomApiException("patient with id " + patientId + " is not found !!!");
		}
		appointment.setDate(new Date());
		appointment.setStatus(false);
		appointment.setPatient(patientService.getOneById(patientId).get());
		appointment.setReceptionist(receptionistService.getOneById(receptionistId).get());
		appointmentService.addOrUpdate(appointment);
	}
	
	@PatchMapping("/{rid}/patients/{pid}/appointments/{aid}")
	public void updatePatientAppointment(@PathVariable("pid") int patientId, @PathVariable("rid") int receptionistId, @PathVariable("aid") int appointmentId, @RequestBody Appointment appointment) throws CustomApiException {
		if(!receptionistService.getOneById(receptionistId).isPresent()) {
			throw new CustomApiException("receptionist with id " + receptionistId + " is not found !!!");
		}
		if(!patientService.getOneById(patientId).isPresent()) {
			throw new CustomApiException("patient with id " + patientId + " is not found !!!");
		}
		if(!appointmentService.getOneById(appointmentId).isPresent()) {
			throw new CustomApiException("appointment with id " + appointmentId + " is not found !!!");
		}
		
		appointment.setId(appointmentId);
		appointmentService.addOrUpdate(appointment);
	}
	
	@GetMapping("/patients/{pid}/appointments")
	public List<Appointment> getPatientAllAppointments(@PathVariable int id) throws CustomApiException {
		if(!patientService.getOneById(id).isPresent()) {
			throw new CustomApiException("patient with id " + id + " is not found !!!");
		}
		
		return patientService.getOneById(id).get().getAppointments();
	}

}
