package com.example.demo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.training.model.Training;
import com.example.demo.training.repository.TrainingRepository;


@SpringBootTest
public class TrainingTests {
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Test
	public void testCreateTraining () {
		Training training = new Training();
		training.setTrainingId(252L);
		training.setActName("Onboarding 3");
		training.setAvailDate("2023-04-25");
		training.setDueDate("2023-07-21");
		trainingRepository.save(training);
		assertNotNull(trainingRepository.findById(252L).get());
		
	}

	private void assertNotNull(Training training) {
	    if (training == null) {
	        throw new AssertionError("Expected non-null training object");
	    }
	}
	
	@Test
	public void testReadAll () {
		List<Training> training = trainingRepository.findAll();
		assertThat(training).size().isGreaterThan(0);
	}
	
	@Test
	public void testTrainingInfo() {
		Training training = trainingRepository.findById(1L).get();
		assertEquals("Onboarding 1", training.getActName());
	}
	
	@Test
	public void testInfoUpdate() {
		Training training = trainingRepository.findById(1L).get();
		training.setActName("Onboarding 1");
		trainingRepository.save(training);
		assertNotEquals("Onboarding", trainingRepository.findById(1L).get().getActName());
	}
	
	@Test
	public void testTraineeDelete() {
		trainingRepository.deleteById(152L);
		assertThat(trainingRepository.existsById(152L)).isFalse();
	}

}
