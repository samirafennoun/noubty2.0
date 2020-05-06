package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import com.chillhub.app.entities.Criticality;

public interface ICriticalityService {

	List<Criticality> getAll();

	Optional<Criticality> getOneById(int id);

	void addOrUpdate(Criticality c);

	void delete(Criticality c);

}