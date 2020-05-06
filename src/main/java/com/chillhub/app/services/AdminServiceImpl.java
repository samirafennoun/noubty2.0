package com.chillhub.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.chillhub.app.dao.AdminDao;
import com.chillhub.app.entities.Admin;

@Service
public class AdminServiceImpl implements IAdminService {
	
	@Autowired
	AdminDao dao;
	
	@Override
	public Optional<Admin> getOneById(int id) {
		return dao.findById(id);
	}

}
