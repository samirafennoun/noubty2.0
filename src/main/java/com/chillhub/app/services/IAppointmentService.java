package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import com.chillhub.app.entities.Appointment;

public interface IAppointmentService {

	List<Appointment> getAll();

	Optional<Appointment> getOneById(int id);

	void addOrUpdate(Appointment a);

	void delete(Appointment a);

}