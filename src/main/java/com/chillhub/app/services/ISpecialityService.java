package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import com.chillhub.app.entities.Speciality;

public interface ISpecialityService {

	List<Speciality> getAll();

	Optional<Speciality> getOneById(int id);

	void addOrUpdate(Speciality s);

	void delete(Speciality s);

}