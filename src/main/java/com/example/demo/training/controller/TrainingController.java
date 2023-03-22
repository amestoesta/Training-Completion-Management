package com.example.demo.training.controller;

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

import com.example.demo.training.model.Training;
import com.example.demo.training.repository.TrainingRepository;
import com.example.demo.training.service.TrainingService;

@RestController
@RequestMapping("/training")
public class TrainingController {
	
	@Autowired
	private TrainingService trainingService;
	
	@Autowired
	private TrainingRepository trainingRepository;
	
	@PostMapping("/save")
	public ResponseEntity createdTraining(@RequestBody Training trainingObj) {
		trainingService.saveTraining(trainingObj);
		return new ResponseEntity(HttpStatus.CREATED);
	}
	
	@GetMapping("/getTraining")
	public List<Training> getTraining(@PathVariable(required = false)Long trainingId){
		return trainingService.getTrainingDetails(trainingId);
	}
	
	@GetMapping("/{trainingId}")
	public List<Training> getTrainingById(@PathVariable(required = false)Long trainingId){
		return trainingService.getTrainingDetails(trainingId);
	}
	
	@GetMapping("/find/{actName}")
	public List<Training> findTrainingContainingByActName(@PathVariable String actName){
		return trainingRepository.findByActNameContaining(actName);
	}
	
	@PutMapping("/{trainingId}")
	public ResponseEntity<Training> updateTraining(@PathVariable Long trainingId, @RequestBody Training trnObj) {
		Optional<Training> training = trainingRepository.findById(trainingId);
		if(training.isPresent()) {
			training.get().setActName(trnObj.getActName());
			training.get().setAvailDate(trnObj.getAvailDate());
			training.get().setDueDate(trnObj.getDueDate());
			return new ResponseEntity<>(trainingRepository.save(training.get()),HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			
		}
	}
	
	@PatchMapping("/update/{trainingId}")
	public Training updateTraineebyFields(@PathVariable Long trainingId,@RequestBody Map<String, Object> fields) {
		return trainingService.updateTrainingFields(trainingId,fields);
	}
	
	@DeleteMapping("/delete/{trainingId}")
	public ResponseEntity removeTraining(@PathVariable Long trainingId) {
		trainingService.deleteTraining(trainingId);
		return new ResponseEntity(HttpStatus.OK);
	}
	

}
