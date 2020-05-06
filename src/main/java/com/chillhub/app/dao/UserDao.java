package com.chillhub.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.chillhub.app.entities.User;

public interface UserDao extends JpaRepository<User, Integer> {
	
	User findByUsername(String username);

}