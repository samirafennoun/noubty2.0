package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.QueuerDao;
import com.chillhub.app.entities.Department;
import com.chillhub.app.entities.Queuer;

@Service
public class QueuerServiceImpl implements IQueuerService {
	
	@Autowired
	QueuerDao dao;
	
	@Override
	public List<Queuer> getAll() {
		return dao.findAll();
	}
	
	@Override
	public Optional<Queuer> getOneById(int id) {
		return dao.findById(id);
	}
	
	@Override
	public void addOrUpdate(Queuer q) {
		dao.save(q);
	}
	
	@Override
	public void delete(Queuer q) {
		dao.delete(q);
	}
	
	@Override
	public List<Queuer> getOrderedListByDepartment(Department department) {
		return dao.findByDepartmentOrderByAppointment_Criticality_IdAscIdAsc(department);
	}
	
	@Override
	public List<Queuer> getOrderedListByDepartmentId(int depId) {
		return dao.findByDepartment_IdOrderByAppointment_Criticality_IdAscIdAsc(depId);
	}
	
	@Override
	public List<Queuer> getOrderedList(Department department) {
		return dao.findByDepartmentOrderByTurnAsc(department);
	}

}
