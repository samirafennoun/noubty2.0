package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.SpecialityDao;
import com.chillhub.app.entities.Speciality;

@Service
public class SpecialityServiceImpl implements ISpecialityService {
	
	@Autowired
	SpecialityDao dao;
	
	@Override
	public List<Speciality> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Speciality> getOneById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void addOrUpdate(Speciality s) {
		dao.save(s);
	}
	
	@Override
	public void delete(Speciality s) {
		dao.delete(s);
	}

}
