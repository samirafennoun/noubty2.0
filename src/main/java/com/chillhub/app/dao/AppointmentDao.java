package com.chillhub.app.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.chillhub.app.entities.Appointment;

@Repository
public interface AppointmentDao extends JpaRepository<Appointment, Integer> {

}
