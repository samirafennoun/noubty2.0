package com.chillhub.app.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Doctor extends User {

	@Column(unique = true)
	private String refMedicale;
	private boolean disponible;
	@Column(unique = true)
	private String room;
	private String absReason;

	@ManyToOne
	@JoinColumn(name = "fk_speciality")
	private Speciality speciality;
	
	@ManyToOne
	@JoinColumn(name = "fk_department")
	private Department department;
	
	@OneToMany(mappedBy = "doctor", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Queuer> queuersList;

	public String getRefMedicale() {
		return refMedicale;
	}

	public void setRefMedicale(String refMedicale) {
		this.refMedicale = refMedicale;
	}

	public boolean isDisponible() {
		return disponible;
	}

	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}

	public Speciality getSpeciality() {
		return speciality;
	}

	public void setSpeciality(Speciality speciality) {
		this.speciality = speciality;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getAbsReason() {
		return absReason;
	}

	public void setAbsReason(String absReason) {
		this.absReason = absReason;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	public List<Queuer> getQueuersList() {
		return queuersList;
	}

	public void setQueuersList(List<Queuer> queuersList) {
		this.queuersList = queuersList;
	}

	@Override
	public String toString() {
		return "Doctor [refMedicale=" + refMedicale + ", disponible=" + disponible + ", room=" + room + ", absReason="
				+ absReason + ", speciality=" + speciality + ", department=" + department + ", queuersList="
				+ queuersList + "]";
	}
}
