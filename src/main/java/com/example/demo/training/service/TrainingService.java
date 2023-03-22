package com.example.demo.training.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.trainee.model.Trainee;
import com.example.demo.training.model.Training;
import com.example.demo.training.repository.TrainingRepository;

@Service
public class TrainingService {
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	public void saveTraining(Training trainingObj) {
		trainingRepository.save(trainingObj);
	}
	
	public List<Training> getTrainingDetails(Long trainingId){
		if (null != trainingId) {
			return trainingRepository.findAllByTrainingId(trainingId);
		} else {
			return trainingRepository.findAll();
		}
	}
	
	public void deleteTraining(Long trainingId) {
		trainingRepository.deleteById(trainingId);
	}

	public Training updateTrainingFields(Long trainingId, Map<String, Object> fields) {
		Optional<Training> training = trainingRepository.findById(trainingId);
		
		if(training.isPresent()) {
		fields.forEach((key, value)->{
			Field field = ReflectionUtils.findField(Training.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, training.get(), value);
		});
		return trainingRepository.save(training.get());
	}
		return null;
	}

}
