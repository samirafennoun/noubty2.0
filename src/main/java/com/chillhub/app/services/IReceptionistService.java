package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import com.chillhub.app.entities.Appointment;
import com.chillhub.app.entities.Receptionist;

public interface IReceptionistService {

	List<Receptionist> getAll();

	Optional<Receptionist> getOneById(int id);

	void addOrUpdate(Receptionist r);

	void delete(Receptionist r);

	Receptionist findByRegCode(String regCode);

	List<Appointment> getCreatedAppointments(Receptionist r);

}