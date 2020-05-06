package com.chillhub.app.services;

import java.util.Optional;

import com.chillhub.app.entities.Admin;

public interface IAdminService {

	Optional<Admin> getOneById(int id);

}