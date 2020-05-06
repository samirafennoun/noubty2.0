package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.ReceptionistDao;
import com.chillhub.app.entities.Appointment;
import com.chillhub.app.entities.Receptionist;

@Service
public class ReceptionistServiceImpl implements IReceptionistService {
	
	@Autowired
	ReceptionistDao dao;
	
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Override
	public List<Receptionist> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Receptionist> getOneById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void addOrUpdate(Receptionist r) {
		r.setPassword(r.getRegCode());
		r.setPassword(bcrypt.encode(r.getPassword()));
		dao.save(r);
	}
	
	@Override
	public void delete(Receptionist r) {
		dao.delete(r);
	}
	
	@Override
	public Receptionist findByRegCode(String regCode) {
		return dao.findByRegCode(regCode);
	}
	
	@Override
	public List<Appointment> getCreatedAppointments(Receptionist r) {
		return r.getCreatedAppointments();
	}

}
