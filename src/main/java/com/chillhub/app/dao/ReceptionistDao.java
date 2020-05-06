package com.chillhub.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Receptionist;

@Repository
public interface ReceptionistDao extends JpaRepository<Receptionist, Integer> {
	
	Receptionist findByRegCode(String regCode);

}
