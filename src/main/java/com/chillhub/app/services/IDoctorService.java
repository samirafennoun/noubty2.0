package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import com.chillhub.app.entities.Doctor;
import com.chillhub.app.entities.Queuer;

public interface IDoctorService {

	List<Doctor> getAll();

	Optional<Doctor> getOneById(int id);

	void addOrUpdate(Doctor p);

	void delete(Doctor p);

	Doctor getByRoom(String room);
	
	public Doctor getByRefMedical(String ref);
	
	public List<Doctor> findDispoDoc();

	void changeDoctorDispo(Doctor doc);

	List<Queuer> getQueueOrderedById(int docId);

	List<Queuer> getQueueOrdered(Doctor doc);

}