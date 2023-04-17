package com.example.demo.training.model;

import java.sql.Date;
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
	private Date availDate;
	private Date dueDate;
	
	
	@JsonIgnore
	@ManyToMany(mappedBy = "assignedTrainings")
	private Set<Trainee> traineeSet = new HashSet<>();


	public Training() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Training(Long trainingId, String actName, Date availDate, Date dueDate, Set<Trainee> traineeSet) {
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


	public Date getAvailDate() {
		return availDate;
	}


	public void setAvailDate(Date availDate) {
		this.availDate = availDate;
	}


	public Date getDueDate() {
		return dueDate;
	}


	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}


	public Set<Trainee> getTraineeSet() {
		return traineeSet;
	}


	public void setTraineeSet(Set<Trainee> traineeSet) {
		this.traineeSet = traineeSet;
	}


	public void setAvailDate(String string) {
		// TODO Auto-generated method stub
		
	}


	public void setDueDate(String string) {
		// TODO Auto-generated method stub
		
	}

	
}
