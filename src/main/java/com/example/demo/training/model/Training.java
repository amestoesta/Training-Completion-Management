package com.example.demo.training.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.trainee.model.Trainee;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "TRAINING")
public class Training {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long trainingId;
	private String actName;
	private LocalDate availDate;
	private LocalDate dueDate;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "assignedTrainings")
	private Set<Trainee> traineeSet = new HashSet<>();


	public Training() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Training(Long trainingId, String actName, LocalDate availDate, LocalDate dueDate, Set<Trainee> traineeSet) {
		super();
		this.trainingId = trainingId;
		this.actName = actName;
		this.availDate = availDate;
		this.dueDate = dueDate;
		this.traineeSet = traineeSet;
	}


	public Long getTrainingId() {
		return trainingId;
	}


	public void setTrainingId(Long trainingId) {
		this.trainingId = trainingId;
	}


	public String getActName() {
		return actName;
	}


	public void setActName(String actName) {
		this.actName = actName;
	}


	public LocalDate getAvailDate() {
		return availDate;
	}


	public void setAvailDate(LocalDate localDate) {
		this.availDate = localDate;
	}


	public LocalDate getDueDate() {
		return dueDate;
	}


	public void setDueDate(LocalDate localDate) {
		this.dueDate = localDate;
	}


	public Set<Trainee> getTraineeSet() {
		return traineeSet;
	}


	public void setTraineeSet(Set<Trainee> traineeSet) {
		this.traineeSet = traineeSet;
	}


	
}
