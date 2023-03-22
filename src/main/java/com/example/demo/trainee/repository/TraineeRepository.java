package com.example.demo.trainee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.trainee.model.Trainee;

public interface TraineeRepository extends JpaRepository<Trainee, Long> {

	
	List<Trainee> findAllByTraineeId(Long traineeId);
	
	List<Trainee> findByNameContaining(String name);
	
	List<Trainee> findByPositionContaining(String position);
}
