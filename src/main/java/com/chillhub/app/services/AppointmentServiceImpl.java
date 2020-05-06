package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.AppointmentDao;
import com.chillhub.app.entities.Appointment;

@Service
public class AppointmentServiceImpl implements IAppointmentService {
	
	@Autowired
	AppointmentDao dao;
	
	@Override
	public List<Appointment> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Appointment> getOneById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void addOrUpdate(Appointment a) {
		dao.save(a);
	}
	
	@Override
	public void delete(Appointment a) {
		dao.delete(a);
	}

}
