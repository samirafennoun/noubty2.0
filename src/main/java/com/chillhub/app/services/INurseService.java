package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import com.chillhub.app.entities.Nurse;
import com.chillhub.app.entities.Queuer;

public interface INurseService {

	List<Nurse> getAll();

	Optional<Nurse> getOneById(int id);

	void addOrUpdate(Nurse nurse);

	void delete(Nurse nurse);

	Nurse findByRef(String ref);

	List<Queuer> getCreatedQueuersList(Nurse nurse);

}