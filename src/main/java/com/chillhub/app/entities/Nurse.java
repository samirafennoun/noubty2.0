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
public class Nurse extends User {

	@Column(unique = true)
	private String refMedicale;

	@OneToMany(mappedBy = "nurse", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Queuer> createdQueuers;
	
	@ManyToOne
	@JoinColumn(name = "fk_department")
	private Department department;

	public String getRefMedicale() {
		return refMedicale;
	}

	public void setRefMedicale(String refMedicale) {
		this.refMedicale = refMedicale;
	}

	@Override
	public String toString() {
		return "Nurse [refMedicale=" + refMedicale + ", createdQueuers=" + createdQueuers + ", department=" + department
				+ "]";
	}

	public List<Queuer> getCreatedQueuers() {
		return createdQueuers;
	}

	public void setCreatedQueuers(List<Queuer> createdQueuers) {
		this.createdQueuers = createdQueuers;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

}
