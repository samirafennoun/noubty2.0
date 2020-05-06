package com.chillhub.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Speciality;

@Repository
public interface SpecialityDao extends JpaRepository<Speciality, Integer>{

}
