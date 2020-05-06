package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.DepartmentDao;
import com.chillhub.app.entities.Department;

@Service
public class DepartmentServiceImpl implements IDepartmentService {
	
	@Autowired
	DepartmentDao dao;
	
	@Override
	public List<Department> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Department> getOneById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void addOrUpdate(Department d) {
		dao.save(d);
	}
	
	@Override
	public void delete(Department d) {
		dao.delete(d);
	}

}
