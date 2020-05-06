package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.PatientDAO;
import com.chillhub.app.entities.Patient;

@Service
public class PatientServiceImpl implements IPatientService {

	@Autowired
	PatientDAO dao;
	
	@Override
	public List<Patient> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Patient> getOneById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void addOrUpdate(Patient p) {
		dao.save(p);
	}
	
	@Override
	public void delete(Patient p) {
		dao.delete(p);
	}
	
	@Override
	public Patient getByCin(String cin) {
		return dao.findByCin(cin);
	}
	
	@Override
	public Patient getByEmail(String email) {
		return dao.findByEmail(email);
	}
	
	@Override
	public Patient getByTel(String tel) {
		return dao.findByTel(tel);
	}
	
	
	
	
}
