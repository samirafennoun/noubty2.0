package com.chillhub.app.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Receptionist extends User {

	@Column(unique = true)
	private String regCode;

	@OneToMany(mappedBy = "receptionist", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Appointment> createdAppointments;

	public String getRegCode() {
		return regCode;
	}

	public void setRegCode(String regCode) {
		this.regCode = regCode;
	}

	@Override
	public String toString() {
		return "Receptionist [regCode=" + regCode + ", createdAppointments=" + createdAppointments + "]";
	}

	public List<Appointment> getCreatedAppointments() {
		return createdAppointments;
	}

	public void setCreatedAppointments(List<Appointment> createdAppointments) {
		this.createdAppointments = createdAppointments;
	}

}
