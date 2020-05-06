package com.chillhub.app.services;

import java.util.List;
import java.util.Optional;

import com.chillhub.app.entities.Department;

public interface IDepartmentService {

	List<Department> getAll();

	Optional<Department> getOneById(int id);

	void addOrUpdate(Department d);

	void delete(Department d);

}