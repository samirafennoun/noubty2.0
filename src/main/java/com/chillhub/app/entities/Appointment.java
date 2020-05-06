package com.chillhub.app.entities;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Appointment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	private String motif;
	private boolean status;

	private float patientTemperature;
	private String patientBloodPressure;
	private float patientWeight;
	private String allergies;

	@OneToMany(mappedBy = "appointment", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JsonIgnore
	private List<Queuer> turns;

	@ManyToOne
	@JoinColumn(name = "fk_patient")
	private Patient patient;

	@ManyToOne
	@JoinColumn(name = "fk_criticality")
	private Criticality criticality;

	@ManyToOne
	@JoinColumn(name = "fk_creator")
	private Receptionist receptionist;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getMotif() {
		return motif;
	}

	public void setMotif(String motif) {
		this.motif = motif;
	}

	public float getPatientTemperature() {
		return patientTemperature;
	}

	public void setPatientTemperature(float patientTemperature) {
		this.patientTemperature = patientTemperature;
	}

	public String getPatientBloodPressure() {
		return patientBloodPressure;
	}

	public void setPatientBloodPressure(String patientBloodPressure) {
		this.patientBloodPressure = patientBloodPressure;
	}

	public float getPatientWeight() {
		return patientWeight;
	}

	public void setPatientWeight(float patientWeight) {
		this.patientWeight = patientWeight;
	}

	public String getAllergies() {
		return allergies;
	}

	public void setAllergies(String allergies) {
		this.allergies = allergies;
	}

	public List<Queuer> getTurns() {
		return turns;
	}

	public void setTurns(List<Queuer> turns) {
		this.turns = turns;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Criticality getCriticality() {
		return criticality;
	}

	public void setCriticality(Criticality criticality) {
		this.criticality = criticality;
	}

	public Receptionist getReceptionist() {
		return receptionist;
	}

	public void setReceptionist(Receptionist receptionist) {
		this.receptionist = receptionist;
	}

	public boolean getStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Appointment [id=" + id + ", date=" + date + ", motif=" + motif + ", status=" + status
				+ ", patientTemperature=" + patientTemperature + ", patientBloodPressure=" + patientBloodPressure
				+ ", patientWeight=" + patientWeight + ", allergies=" + allergies + ", turns=" + turns + ", patient="
				+ patient + ", criticality=" + criticality + ", receptionist=" + receptionist + "]";
	}

}
