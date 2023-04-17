package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.trainee.model.Trainee;
import com.example.demo.trainee.repository.TraineeRepository;


@SpringBootTest
class TrackingCompletionApplicationTests {

	@Autowired
	private TraineeRepository traineeRepository;
	
	@Test
	public void testCreateTrn () {
		Trainee trainee = new Trainee();
		trainee.setTraineeId(1L);
		trainee.setName("Kean");
		trainee.setPosition("Lead Developer");
		trainee.setStartDate("2023-04-25");
		trainee.setEndDate("2023-07-21");
		traineeRepository.save(trainee);
		assertNotNull(traineeRepository.findById(1L).get());
		
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
		Trainee trainee = traineeRepository.findById(502L).get();
		trainee.setPosition("Full Stack Developer");
		traineeRepository.save(trainee);
		assertNotEquals("Lead Developer", traineeRepository.findById(502L).get().getPosition());
	}
	
	@Test
	public void testTraineeDelete() {
		traineeRepository.deleteById(552L);
		assertThat(traineeRepository.existsById(552L)).isFalse();
	}
}
