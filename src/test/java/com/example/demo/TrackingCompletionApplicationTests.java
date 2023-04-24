package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import com.example.demo.trainee.model.Trainee;
import com.example.demo.trainee.repository.TraineeRepository;
import com.example.demo.trainee.service.TraineeService;
import com.example.demo.training.model.Training;
import com.example.demo.training.repository.TrainingRepository;


@SpringBootTest
class TrackingCompletionApplicationTests {

	@Autowired
	private TraineeRepository traineeRepository;
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Autowired
	private TraineeService traineeService;
	
	@Test
	public void testCreateTrn () {
		Trainee trainee = new Trainee();
		trainee.setTraineeId(52L);
		trainee.setName("Froilan Zaguirre");
		trainee.setPosition("Lead Developer");
		trainee.setStartDate(LocalDate.now());
		trainee.setEndDate(LocalDate.now().plusMonths(3));
		traineeRepository.save(trainee);
		assertNotNull(traineeRepository.findById(trainee.getTraineeId()).get());
	}

	private void assertNotNull(Trainee trainee) {
	    if (trainee == null) {
	        throw new AssertionError("Expected non-null trainee object");
	    }
	}
	
	@Test
	public void testReadAll () {
		List<Trainee> trainee = traineeRepository.findAll();
		assertThat(trainee).size().isGreaterThan(0);
	}
	
	@Test
	public void testTraineeInfo() {
		Trainee trainee = traineeRepository.findById(502L).get();
		assertEquals("Full Stack Developer", trainee.getPosition());
	}
	
	@Test
	public void testInfoUpdate() {
		Trainee trainee = traineeRepository.findById(852L).get();
		trainee.setPosition("Full Stack Developer");
		trainee.setEndDate(LocalDate.now().plusMonths(6)); 
		traineeRepository.save(trainee);
		assertNotEquals("Lead Developer", traineeRepository.findById(852L).get().getPosition());
	}
	
	@Test
	public void testTraineeDelete() {
		traineeRepository.deleteById(552L);
		assertThat(traineeRepository.existsById(552L)).isFalse();
	}
	
	@Test
	public void testAssignTrainee() {
		Trainee trainee = traineeRepository.findById(902L).get();
		Training training = trainingRepository.findById(352L).get();
		
		Long traineeId = 902L;
		Long trainingId = 352L;
		traineeRepository.save(trainee);
		assertTrue(traineeService.assignTrainingToTrainee(traineeId, trainingId));
	}

	private void assertTrue(Trainee assignTrainingToTrainee) {
		// TODO Auto-generated method stub
		
	}
	
	
	
	
}
