package com.chillhub.app.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Doctor;

@Repository
public interface DoctorDao extends JpaRepository<Doctor, Integer> {

	List<Doctor> findByDisponible(boolean dispo);
	Doctor findByRefMedicale(String refMedicale);
	Doctor findByRoom(String room);
}
