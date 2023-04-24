package com.example.demo.trainee.model;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import com.example.demo.training.model.Training;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import lombok.Data;

@Entity
@Data
public class Trainee {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long traineeId;
	private String name;
	private String position;
	private LocalDate startDate;
	private LocalDate endDate;
	
	@ManyToMany
	@JoinTable(name = "trainee_activity",
			joinColumns = @JoinColumn(name = "trainee_id"),
			inverseJoinColumns = @JoinColumn(name = "training_id")
	)
	private Set<Training> assignedTrainings = new HashSet<>();

	public Trainee() {
		
	}

	public Trainee(Long traineeId, String name, String position, LocalDate startDate, LocalDate endDate,
			Set<Training> assignedTrainings) {
		super();
		this.traineeId = traineeId;
		this.name = name;
		this.position = position;
		this.startDate = startDate;
		this.endDate = endDate;
		this.assignedTrainings = assignedTrainings;
	}



	public Long getTraineeId() {
		return traineeId;
	}



	public void setTraineeId(Long traineeId) {
		this.traineeId = traineeId;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getPosition() {
		return position;
	}



	public void setPosition(String position) {
		this.position = position;
	}



	public LocalDate getStartDate() {
		return startDate;
	}



	public void setStartDate(LocalDate localDate) {
		this.startDate = localDate;
	}



	public LocalDate getEndDate() {
		return endDate;
	}



	public void setEndDate(LocalDate localDate) {
		this.endDate = localDate;
	}



	public Set<Training> getAssignedTrainings() {
		return assignedTrainings;
	}



	public void setAssignedTrainings(Set<Training> assignedTrainings) {
		this.assignedTrainings = assignedTrainings;
	}

}
