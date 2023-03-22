package com.example.demo.training.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.training.model.Training;

public interface TrainingRepository extends JpaRepository<Training, Long> {
	
	List<Training> findAllByTrainingId(Long trainingId);
	
	List<Training> findByActNameContaining(String actName);

}
