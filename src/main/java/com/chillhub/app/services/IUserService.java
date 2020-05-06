package com.chillhub.app.services;

import java.util.List;

import com.chillhub.app.entities.User;

public interface IUserService {

	void addUser(User user);

	List<User> getUsers();

	User getOneById(int id);

}