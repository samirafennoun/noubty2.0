package com.chillhub.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.UserDao;
import com.chillhub.app.entities.User;

@Service
public class UserServiceImpl implements IUserService {
	
	@Autowired
	UserDao dao;
	
	BCryptPasswordEncoder bcrypt = new BCryptPasswordEncoder();
	
	@Override
	public void addUser(User user) {
		user.setPassword(bcrypt.encode(user.getPassword()));
		dao.save(user);
	}
	
	@Override
	public List<User> getUsers() {
		return dao.findAll();
	}
	
	@Override
	public User getOneById(int id) {
		return dao.findById(id).get();
	}

}