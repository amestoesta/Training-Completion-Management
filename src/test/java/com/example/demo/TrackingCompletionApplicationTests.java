package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

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
		trainee.setName("Froilan Jesus Zaguirre");
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
	public void testAssignTrainingToTrainee() {
	    // get an existing trainee and training from the repository
	    Long traineeId = 153L;
	    Long trainingId = 52L;
	    Trainee trainee = traineeRepository.findById(traineeId).get();
	    Training training = trainingRepository.findById(trainingId).get();
	    
	    // call the method to assign the training to the trainee
	    Trainee assignedTrainee = traineeService.assignTrainingToTrainee(traineeId, trainingId);
	    
	    // check if the training was added to the trainee's assigned trainings
	    Set<Training> assignedTrainings = assignedTrainee.getAssignedTrainings();
	    assertThat(assignedTrainings.contains(training));
	}

	
	
}
