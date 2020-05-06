package com.chillhub.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Nurse;

@Repository
public interface NurseDao extends JpaRepository<Nurse, Integer>{
	
	Nurse findByRefMedicale(String ref);

}
