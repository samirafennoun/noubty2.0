package com.chillhub.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.chillhub.app.entities.Admin;
import com.chillhub.app.entities.Criticality;
import com.chillhub.app.entities.Department;
import com.chillhub.app.entities.Doctor;
import com.chillhub.app.entities.Nurse;
import com.chillhub.app.entities.Receptionist;
import com.chillhub.app.entities.Role;
import com.chillhub.app.entities.Speciality;
import com.chillhub.app.services.IAdminService;
import com.chillhub.app.services.ICriticalityService;
import com.chillhub.app.services.IDepartmentService;
import com.chillhub.app.services.IDoctorService;
import com.chillhub.app.services.INurseService;
import com.chillhub.app.services.IReceptionistService;
import com.chillhub.app.services.ISpecialityService;

@RestController
@CrossOrigin
@RequestMapping("api/admin")
public class AdminController {
	
	@Autowired
	IAdminService adminService;
	
	@Autowired
	INurseService nurseService;
	
	@Autowired
	IReceptionistService receptionistService;
	
	@Autowired
	IDoctorService doctorService;
	
	@Autowired
	ISpecialityService specialityService;
	
	@Autowired
	IDepartmentService departmentService;
	
	@Autowired
	ICriticalityService criticalityService;
	
	@GetMapping("{id}")
	public Admin getAdminById(@PathVariable int id) throws CustomApiException {
		if(!adminService.getOneById(id).isPresent()) {
			throw new CustomApiException("admin with id " + id + " is not found !!!");
		}
		return adminService.getOneById(id).get();
	}
	
//	get lists of nurses, receptionists and doctors methods
	
	@GetMapping("nurses")
	public List<Nurse> getAllNurses() {
		return nurseService.getAll();
	}
	
	@GetMapping("receptionists")
	public List<Receptionist> getAllReceptionists() {
		return receptionistService.getAll();
	}
	
	@GetMapping("doctors")
	public List<Doctor> getAllDoctors() {
		return doctorService.getAll();
	}
	
//	get a nurse, a receptionist, or a doctor by its id
	
	@GetMapping("nurses/{id}")
	public Nurse getNurseById(@PathVariable("id") int id) throws CustomApiException {
		if(!nurseService.getOneById(id).isPresent()) {
			throw new CustomApiException("nurse with id " + id + " is not found !!!");
		}
		return nurseService.getOneById(id).get();
	}
	
	@GetMapping("receptionists/{id}")
	public Receptionist getReceptionistById(@PathVariable("id") int id) throws CustomApiException {
		if(!receptionistService.getOneById(id).isPresent()) {
			throw new CustomApiException("receptionist with id " + id + " is not found !!!");
		}
		return receptionistService.getOneById(id).get();
	}
	
	@GetMapping("doctors/{id}")
	public Doctor getDoctorById(@PathVariable("id") int id) throws CustomApiException {
		if(!doctorService.getOneById(id).isPresent()) {
			throw new CustomApiException("doctor with id " + id + " is not found !!!");
		}
		return doctorService.getOneById(id).get();
	}
	
//	add a nurse, receptionist or a doctor
	
	@PostMapping("nurses")
	public void addNurse(@RequestBody Nurse nurse) {
		Role role = new Role();
		role.setId(3);
		nurse.setRole(role);
		nurseService.addOrUpdate(nurse);
	}
	
	@PostMapping("receptionists")
	public void addReceptionist(@RequestBody Receptionist receptionist) {
		Role role = new Role();
		role.setId(2);
		receptionist.setRole(role);
		receptionistService.addOrUpdate(receptionist);
	}
	
	@PostMapping("doctors")
	public void addDoctor(@RequestBody Doctor doctor) {
		Role role = new Role();
		role.setId(4);
		doctor.setRole(role);
		doctor.setDisponible(false);
		doctor.setAbsReason("has just been assigned");
		doctorService.addOrUpdate(doctor);
	}
	
//	update a nurse, receptionist or a doctor
	
	@PatchMapping("nurses/{id}")
	public void updateNurse(@PathVariable("id") int id, @RequestBody Nurse nurse) throws CustomApiException {
		if(!nurseService.getOneById(id).isPresent()) {
			throw new CustomApiException("nurse with id " + id + " is not found !!!");
		}
		nurse.setId(id);
		Role role = new Role();
		role.setId(3);
		nurse.setRole(role);
		nurseService.addOrUpdate(nurse);
	}
	
	@PatchMapping("receptionists/{id}")
	public void updateReceptionist(@PathVariable("id") int id, @RequestBody Receptionist receptionist) throws CustomApiException {
		if(!receptionistService.getOneById(id).isPresent()) {
			throw new CustomApiException("receptionist with id " + id + " is not found !!!");
		}
		receptionist.setId(id);
		Role role = new Role();
		role.setId(2);
		receptionist.setRole(role);
		receptionistService.addOrUpdate(receptionist);
	}
	
	@PatchMapping("doctors/{id}")
	public void updateDoctor(@PathVariable("id") int id, @RequestBody Doctor doctor) throws CustomApiException {
		if(!doctorService.getOneById(id).isPresent()) {
			throw new CustomApiException("doctor with id " + id + " is not found !!!");
		}
		doctor.setId(id);
		Role role = new Role();
		role.setId(4);
		doctor.setRole(role);
		doctorService.addOrUpdate(doctor);
	}
	
//	delete a nurse, receptionist or a doctor
	
	@DeleteMapping("nurses/{id}")
	public void deleteNurse(@PathVariable("id") int id) throws CustomApiException {
		if(!nurseService.getOneById(id).isPresent()) {
			throw new CustomApiException("nurse with id " + id + " is not found !!!");
		}
		nurseService.delete(nurseService.getOneById(id).get());
	}
	
	@DeleteMapping("receptionists/{id}")
	public void deleteReceptionist(@PathVariable("id") int id) throws CustomApiException {
		if(!receptionistService.getOneById(id).isPresent()) {
			throw new CustomApiException("receptionist with id " + id + " is not found !!!");
		}
		receptionistService.delete(receptionistService.getOneById(id).get());
	}
	
	@DeleteMapping("doctors/{id}")
	public void deleteDoctor(@PathVariable("id") int id) throws CustomApiException {
		if(!doctorService.getOneById(id).isPresent()) {
			throw new CustomApiException("doctor with id " + id + " is not found !!!");
		}
		doctorService.delete(doctorService.getOneById(id).get());
	}
	
//	****************************************************************************************************
	
	@GetMapping("specialities")
	public List<Speciality> getAllSpecialities() {
		return specialityService.getAll();
	}
	
	@GetMapping("departments")
	public List<Department> getAllDepartments() {
		return departmentService.getAll();
	}
	
	@GetMapping("criticalities")
	public List<Criticality> getAllCriticalities() {
		return criticalityService.getAll();
	}
	
	@GetMapping("specialities/{id}")
	public Speciality getSpecialityById(@PathVariable("id") int id) throws CustomApiException {
		if(!specialityService.getOneById(id).isPresent()) {
			throw new CustomApiException("speciality with id " + id + " is not found !!!");
		}
		return specialityService.getOneById(id).get();
	}
	
	@GetMapping("departments/{id}")
	public Department getDepartmentById(@PathVariable("id") int id) throws CustomApiException {
		if(!departmentService.getOneById(id).isPresent()) {
			throw new CustomApiException("department with id " + id + " is not found !!!");
		}
		return departmentService.getOneById(id).get();
	}
	
	@GetMapping("criticalities/{id}")
	public Criticality getCriticalityById(@PathVariable("id") int id) throws CustomApiException {
		if(!criticalityService.getOneById(id).isPresent()) {
			throw new CustomApiException("criticality with id " + id + " is not found !!!");
		}
		return criticalityService.getOneById(id).get();
	}
	
	@PostMapping("specialities")
	public void addSpeciality(@RequestBody Speciality speciality) {
		specialityService.addOrUpdate(speciality);
	}
	
	@PostMapping("departments")
	public void addDepartment(@RequestBody Department department) {
		departmentService.addOrUpdate(department);
	}
	
	@PostMapping("criticalities")
	public void addCriticality(@RequestBody Criticality criticality) {
		criticalityService.addOrUpdate(criticality);
	}
	
	@PatchMapping("specialities/{id}")
	public void updateSpeciality(@PathVariable("id") int id, @RequestBody Speciality speciality) throws CustomApiException {
		if(!specialityService.getOneById(id).isPresent()) {
			throw new CustomApiException("speciality with id " + id + " is not found !!!");
		}
		speciality.setId(id);
		specialityService.addOrUpdate(speciality);
	}
	
	@PatchMapping("departments/{id}")
	public void updateDepartment(@PathVariable("id") int id, @RequestBody Department department) throws CustomApiException {
		if(!departmentService.getOneById(id).isPresent()) {
			throw new CustomApiException("department with id " + id + " is not found !!!");
		}
		department.setId(id);
		departmentService.addOrUpdate(department);
	}
	
	@PatchMapping("criticalities/{id}")
	public void updateCriticality(@PathVariable("id") int id, @RequestBody Criticality criticality) throws CustomApiException {
		if(!criticalityService.getOneById(id).isPresent()) {
			throw new CustomApiException("criticality with id " + id + " is not found !!!");
		}
		criticality.setId(id);
		criticalityService.addOrUpdate(criticality);
	}
	
	@DeleteMapping("specialities/{id}")
	public void deleteSpeciality(@PathVariable("id") int id) throws CustomApiException {
		if(!specialityService.getOneById(id).isPresent()) {
			throw new CustomApiException("speciality with id " + id + " is not found !!!");
		}
		specialityService.delete(specialityService.getOneById(id).get());
	}
	
	@DeleteMapping("departments/{id}")
	public void deleteDepartment(@PathVariable("id") int id) throws CustomApiException {
		if(!departmentService.getOneById(id).isPresent()) {
			throw new CustomApiException("department with id " + id + " is not found !!!");
		}
		departmentService.delete(departmentService.getOneById(id).get());
	}
	
	@DeleteMapping("criticalities/{id}")
	public void deleteCriticality(@PathVariable("id") int id) throws CustomApiException {
		if(!criticalityService.getOneById(id).isPresent()) {
			throw new CustomApiException("criticality with id " + id + " is not found !!!");
		}
		criticalityService.delete(criticalityService.getOneById(id).get());
	}
}
