package com.chillhub.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Admin;

@Repository
public interface AdminDao extends JpaRepository<Admin, Integer> {

}
