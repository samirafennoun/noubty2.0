package com.chillhub.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Department;

@Repository
public interface DepartmentDao extends JpaRepository<Department, Integer> {
	
	//get list of queuers ordered

}
