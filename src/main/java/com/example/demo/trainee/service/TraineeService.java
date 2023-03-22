package com.example.demo.trainee.service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;

import com.example.demo.trainee.model.Trainee;
import com.example.demo.training.model.Training;
import com.example.demo.trainee.repository.TraineeRepository;
import com.example.demo.training.repository.TrainingRepository;

@Service
public class TraineeService {
	
	@Autowired
	private TraineeRepository traineeRepository;
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	
	//save trainee
	public void saveTrainee(Trainee trnObjc) {
		traineeRepository.save(trnObjc);
	}
	
	//display list of trainees
	public List<Trainee> getTraineeDetails(Long traineeId){
		if (null != traineeId) {
			return traineeRepository.findAllByTraineeId(traineeId);//if not null display id info
		} else {
			return traineeRepository.findAll();//else display all trainee
		}
	}
	
	// delete trainee
	public void deleteTrainee(Long traineeId) {
		traineeRepository.deleteById(traineeId);
	}

	//assign trainings
	public Trainee assignTrainingToTrainee(Long traineeId, Long trainingId) {
		Set<Training> trainingSet = null;
		Trainee trainee = traineeRepository.findById(traineeId).get();
		Training training = trainingRepository.findById(trainingId).get();
		trainingSet = trainee.getAssignedTrainings();
		trainingSet.add(training);
		trainee.setAssignedTrainings(trainingSet);
		return traineeRepository.save(trainee);
	}
	
	//update trainee specific fields
	public Trainee updateTraineeFields(Long traineeId, Map<String, Object> fields) {
		Optional<Trainee> trainee = traineeRepository.findById(traineeId);
	
		if(trainee.isPresent()) {
		fields.forEach((key, value)->{
			Field field = ReflectionUtils.findField(Trainee.class, key);
			field.setAccessible(true);
			ReflectionUtils.setField(field, trainee.get(), value);
		});
		return traineeRepository.save(trainee.get());
	}
	return null;
	}

}
