package com.chillhub.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Patient;

@Repository
public interface PatientDAO extends JpaRepository<Patient, Integer> {
	
	Patient findByCin(String cin);
	Patient findByEmail(String email);
	Patient findByTel(String tel);
}
