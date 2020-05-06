package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import com.chillhub.app.entities.Patient;

public interface IPatientService {

	List<Patient> getAll();

	Optional<Patient> getOneById(int id);

	void addOrUpdate(Patient p);

	void delete(Patient p);

	Patient getByCin(String cin);

	Patient getByEmail(String email);

	Patient getByTel(String tel);

}