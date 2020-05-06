package com.chillhub.app.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chillhub.app.custom_exceptions.CustomApiException;
import com.chillhub.app.entities.Appointment;
import com.chillhub.app.entities.Criticality;
import com.chillhub.app.entities.Department;
import com.chillhub.app.entities.Doctor;
import com.chillhub.app.entities.Nurse;
import com.chillhub.app.entities.Queuer;
import com.chillhub.app.services.IAppointmentService;
import com.chillhub.app.services.ICriticalityService;
import com.chillhub.app.services.IDoctorService;
import com.chillhub.app.services.INurseService;
import com.chillhub.app.services.IQueuerService;

@RestController
@CrossOrigin
@RequestMapping("api/nurse")
public class NurseController {
	
	@Autowired
	INurseService nurseService;
	
	@Autowired
	IAppointmentService appointmentService;
	
	@Autowired
	IQueuerService queuerService;
	
	@Autowired
	IDoctorService doctorService;
	
	@Autowired
	ICriticalityService criticallityService;
	
	@GetMapping("/{id}")
	public Nurse getNurseById(@PathVariable int id) throws CustomApiException{
		if(!nurseService.getOneById(id).isPresent()) {
			throw new CustomApiException("nurse with id " + id + " is not found !!!");
		}
		return nurseService.getOneById(id).get();
	}
	
	@GetMapping("/appointments")
	public List<Appointment> getAllAppointments() {
		return appointmentService.getAll();
	}
	
	@GetMapping("/appointments/today")
	public List<Appointment> getTodaysAppointments() {
		List<Appointment> allAppointments = appointmentService.getAll();
		List<Appointment> todayList = new ArrayList<Appointment>();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		try {
			Date today = sdf.parse(sdf.format(new Date()));
			for(Appointment appointment: allAppointments) {
				Date appointmentDay = sdf.parse(sdf.format(appointment.getDate()));
				if(appointmentDay.compareTo(today) == 0) {
					todayList.add(appointment);
				}
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return todayList;
	}
	
	@GetMapping("/appointments/{id}")
	public Appointment getAppointmentById(@PathVariable int id) throws CustomApiException {
		if(!appointmentService.getOneById(id).isPresent()) {
			throw new CustomApiException("appointment with id " + id + " is not found !!!");
		}
		
		return appointmentService.getOneById(id).get();
	}
	
	private void classifyQueue(int nurseId) throws CustomApiException {
		if(!nurseService.getOneById(nurseId).isPresent()) {
			throw new CustomApiException("nurse with id " + nurseId + " is not found !!!");
		}
		List<Queuer> allDepartmentQueuers =  queuerService.getOrderedListByDepartment(nurseService.getOneById(nurseId).get().getDepartment());
		int i = 1;
		for(Queuer queuer: allDepartmentQueuers) {
			queuer.setTurn(i);
			queuerService.addOrUpdate(queuer);
			i++;
		}
	}
	
	@GetMapping("/{nid}/queuers")
	public List<Queuer> getDepartmentNotAssignedQueue(@PathVariable int nid) throws CustomApiException {
		if(!nurseService.getOneById(nid).isPresent()) {
			throw new CustomApiException("nurse with id " + nid + " is not found !!!");
		}
		List<Queuer> allDepartmentQueuers =  queuerService.getOrderedList(nurseService.getOneById(nid).get().getDepartment());
		List<Queuer> queue = new ArrayList<Queuer>();
		for(Queuer queuer: allDepartmentQueuers) {
			if(queuer.getDoctor() == null && queuer.getAppointment().getStatus() == false) {
				queue.add(queuer);
			}
		}
		return queue;
	}
	
	@GetMapping("/{nid}/doctors")
	public List<Doctor> getDepartmentDoctors(@PathVariable int nid) throws CustomApiException {
		if(!nurseService.getOneById(nid).isPresent()) {
			throw new CustomApiException("nurse with id " + nid + " is not found !!!");
		}
		return nurseService.getOneById(nid).get().getDepartment().getDoctors();
	}
	
	@PostMapping("/{nid}/appointments/{aid}/add-to-queue")
	public void addQueuer(@PathVariable("nid") int nurseId, @PathVariable("aid") int appointmentId) throws Exception {
		if(!nurseService.getOneById(nurseId).isPresent()) {
			throw new CustomApiException("nurse with id " + nurseId + " is not found !!!");
		}
		if(!appointmentService.getOneById(appointmentId).isPresent()) {
			throw new CustomApiException("appointment with id " + appointmentId + " is not found !!!");
		}
		List<Queuer> allDepartmentQueuers =  queuerService.getOrderedList(nurseService.getOneById(nurseId).get().getDepartment());
		for(Queuer queuer: allDepartmentQueuers) {
			if(queuer.getAppointment() == appointmentService.getOneById(appointmentId).get() && queuer.getDepartment() == nurseService.getOneById(nurseId).get().getDepartment())
				throw new Exception("you can't add appointment to queue twice !!");
		}
		Queuer queuer = new Queuer();
		queuer.setAppointment(appointmentService.getOneById(appointmentId).get());
		queuer.setDepartment(nurseService.getOneById(nurseId).get().getDepartment());
		queuer.setNurse(nurseService.getOneById(nurseId).get());
		queuer.setRecheck(false);
		queuerService.addOrUpdate(queuer);
		queuer.setRef(nurseService.getOneById(nurseId).get().getDepartment().getCodeDep() + " - " + queuer.getId());
		queuerService.addOrUpdate(queuer);
		classifyQueue(nurseId);
		
	}
	
//	@PatchMapping("{nid}/queuers/{qid}")
//	public void updateQueuerStatus(@PathVariable("nid") int nurseId, @PathVariable("qid") int queuerId) throws CustomApiException {
//		if(!nurseService.getOneById(nurseId).isPresent()) {
//			throw new CustomApiException("nurse with id " + nurseId + " is not found !!!");
//		}
//		if(!queuerService.getOneById(queuerId).isPresent()) {
//			throw new CustomApiException("queuer with id " + queuerId + " is not found !!!");
//		}
//		Queuer q = new Queuer();
//		q.setId(queuerId);
//		q.setRecheck(!q.isRecheck());
//		queuerService.addOrUpdate(q);
//	}
	
	@PatchMapping("{nid}/queuers/{qid}/absent-or-recheck")
	public void reorderQueue(@PathVariable("nid") int nurseId, @PathVariable("qid") int queuerId) throws CustomApiException {
		if(!nurseService.getOneById(nurseId).isPresent()) {
			throw new CustomApiException("nurse with id " + nurseId + " is not found !!!");
		}
		if(!queuerService.getOneById(queuerId).isPresent()) {
			throw new CustomApiException("queuer with id " + queuerId + " is not found !!!");
		}
		Department dep = nurseService.getOneById(nurseId).get().getDepartment();
		Queuer q = queuerService.getOneById(queuerId).get();
		q.setId(queuerId);
		q.setRecheck(!q.isRecheck());
		q.setTurn(queuerService.getOrderedList(dep).size()+1);
		queuerService.addOrUpdate(q);
		
	}
	
	@DeleteMapping("{nid}/queuers/{qid}")
	public void deleteQueuer(@PathVariable("nid") int nurseId, @PathVariable("qid") int queuerId) throws CustomApiException {
		if(!nurseService.getOneById(nurseId).isPresent()) {
			throw new CustomApiException("nurse with id " + nurseId + " is not found !!!");
		}
		if(!queuerService.getOneById(queuerId).isPresent()) {
			throw new CustomApiException("queuer with id " + queuerId + " is not found !!!");
		}
		queuerService.delete(queuerService.getOneById(queuerId).get());
	}
	
	@PatchMapping("{nid}/doctors/{did}/change-dispo")
	public void changeDoctorDisponibility(@PathVariable("nid") int nurseId, @PathVariable("did") int doctorId) throws CustomApiException {
		if(!nurseService.getOneById(nurseId).isPresent()) {
			throw new CustomApiException("nurse with id " + nurseId + " is not found !!!");
		}
		if(!doctorService.getOneById(doctorId).isPresent()) {
			throw new CustomApiException("doctor with id " + doctorId + " is not found !!!");
		}
		doctorService.changeDoctorDispo(doctorService.getOneById(doctorId).get());
	}
	
	@PatchMapping("queuers/{qid}/assign-to-doctor/{did}")
	public void assignQueuerToDoctor(@PathVariable("did") int doctorId, @PathVariable("qid") int queuerId) throws CustomApiException {
		if(!doctorService.getOneById(doctorId).isPresent()) {
			throw new CustomApiException("doctor with id " + doctorId + " is not found !!!");
		}
		if(!queuerService.getOneById(queuerId).isPresent()) {
			throw new CustomApiException("queuer with id " + queuerId + " is not found !!!");
		}
		Queuer q = queuerService.getOneById(queuerId).get();
		q.setDoctor(doctorService.getOneById(doctorId).get());
		queuerService.addOrUpdate(q);
		
	}
	
	@PatchMapping("/appointments/{aid}/change-status")
	public void changeAppointmentStatus(@PathVariable("aid") int id) throws CustomApiException {
		if(!appointmentService.getOneById(id).isPresent()) {
			throw new CustomApiException("appointment with id " + id + " is not found !!!");
		}
		
		Appointment a = appointmentService.getOneById(id).get();
		a.setStatus(!a.getStatus());
		appointmentService.addOrUpdate(a);
	}
	
	@PutMapping("/appointments/{aid}")
	public void completAppointment(@PathVariable("aid") int id, @RequestBody Appointment appointment) throws CustomApiException {
		if(!appointmentService.getOneById(id).isPresent()) {
			throw new CustomApiException("appointment with id " + id + " is not found !!!");
		}
		appointment.setId(id);
		appointmentService.addOrUpdate(appointment);
	}
	
	@GetMapping("/criticalities")
	public List<Criticality> getAllCriticalities() {
		return criticallityService.getAll();
	}
	
}
