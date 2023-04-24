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

import com.example.demo.training.model.Training;
import com.example.demo.training.repository.TrainingRepository;


@SpringBootTest
public class TrainingTests {
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@Test
	public void testCreateTraining () {
		Training training = new Training();
		training.setTrainingId(1L);
		training.setActName("Research 9");
		training.setAvailDate(LocalDate.now());
		training.setDueDate(LocalDate.now().plusDays(2));
		trainingRepository.save(training);
		assertNotNull(trainingRepository.findById(training.getTrainingId()).get());
		
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
