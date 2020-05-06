package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.CriticalityDao;
import com.chillhub.app.entities.Criticality;

@Service
public class CriticalityServiceImpl implements ICriticalityService {
	
	@Autowired
	CriticalityDao dao;
	
	@Override
	public List<Criticality> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Criticality> getOneById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void addOrUpdate(Criticality c) {
		dao.save(c);
	}
	
	@Override
	public void delete(Criticality c) {
		dao.delete(c);
	}

}
