package com.example.demo.trainee.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.trainee.model.Trainee;
import com.example.demo.trainee.repository.TraineeRepository;
import com.example.demo.trainee.service.TraineeService;

@RestController //return JSON format
@RequestMapping("/trainee") //define web service endpoints 
public class TraineeController {
	
	@Autowired // allows to automatically wire/inject dependencies	
	private TraineeService traineeService;
	
	@Autowired
	private TraineeRepository traineeRepository;
	
	@PostMapping("/save") //save trainee
	public ResponseEntity<Trainee> saveTrainee(@RequestBody Trainee trnObjc){
		traineeService.saveTrainee(trnObjc);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	

	@GetMapping("/getTrainees")// retrieve list of trainees
	public List<Trainee> getTrainee(@PathVariable(required = false) Long traineeId){
		return traineeService.getTraineeDetails(traineeId);
	}
	
	@GetMapping("/{traineeId}")// retrieve specific trainee by ID
		public List<Trainee> getTraineebyId(@PathVariable(required = false) Long traineeId){
			return traineeService.getTraineeDetails(traineeId);
		}	
		
		@GetMapping("/find/{name}")// retrieve specific trainee by name
	public List<Trainee> findTraineeContainingByName(@PathVariable String name){
							return traineeRepository.findByNameContaining(name);
	}	
	
	@GetMapping("/search/{position}") // retrieve specific trainee by position
	public List<Trainee> findTraineeContainingByPosition(@PathVariable String position){
		return traineeRepository.findByPositionContaining(position);
	}
	
	@DeleteMapping("delete/{traineeId}")// delete specific trainee by ID
	public ResponseEntity removeTrainee(@PathVariable Long traineeId) {
		traineeService.deleteTrainee(traineeId);
		return new ResponseEntity(HttpStatus.OK);
	}
	
	@PutMapping("/{traineeId}/training/{trainingId}") // assign trainings
	public Trainee assignTrainingToTrainee(
				@PathVariable Long traineeId,
				@PathVariable Long trainingId
	) {
		return traineeService.assignTrainingToTrainee(traineeId, trainingId);
	}
	
	@PutMapping("/{traineeId}")// update trainee by ID
	public ResponseEntity<Trainee> updateTrainee(@PathVariable Long traineeId, @RequestBody Trainee trnObjc) {
		Optional<Trainee> trainee = traineeRepository.findById(traineeId);
		if(trainee.isPresent()) {
			trainee.get().setName(trnObjc.getName());
			trainee.get().setPosition(trnObjc.getPosition());
			trainee.get().setStartDate(trnObjc.getStartDate());
			trainee.get().setEndDate(trnObjc.getEndDate());
			return new ResponseEntity<>(traineeRepository.save(trainee.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	@PatchMapping("/update/{traineeId}") // update specific field
	public Trainee updateTraineebyFields(@PathVariable Long traineeId,@RequestBody Map<String, Object> fields) {
		return traineeService.updateTraineeFields(traineeId,fields);
	}
	
} 	
