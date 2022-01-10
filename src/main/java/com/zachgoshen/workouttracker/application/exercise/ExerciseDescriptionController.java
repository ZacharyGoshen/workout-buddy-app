package com.zachgoshen.workouttracker.application.exercise;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercise-descriptions")
public class ExerciseDescriptionController {
	
	private final ExerciseDescriptionQueryApplicationService exerciseDescriptionQueryService;
	private final ExerciseDescriptionUpdateApplicationService exerciseDescriptionUpdateService;
	private final ExerciseDescriptionDeleteApplicationService exerciseDescriptionDeleteService;
	
	public ExerciseDescriptionController(
			ExerciseDescriptionQueryApplicationService exerciseDescriptionQueryService, 
			ExerciseDescriptionUpdateApplicationService exerciseDescriptionUpdateService,
			ExerciseDescriptionDeleteApplicationService exerciseDescriptionDeleteService) {
		
		this.exerciseDescriptionQueryService = exerciseDescriptionQueryService;
		this.exerciseDescriptionUpdateService = exerciseDescriptionUpdateService;
		this.exerciseDescriptionDeleteService = exerciseDescriptionDeleteService;
	}
	
	@GetMapping("")
	public List<ExerciseDescriptionDto> findAll() {
		return exerciseDescriptionQueryService.findAll();
	}
	
	@PutMapping("")
	public void update(@RequestBody ExerciseDescriptionDto description) throws NonexistentExerciseDescriptionException {
		exerciseDescriptionUpdateService.update(description);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) throws UndeletableExerciseDescriptionException {
		exerciseDescriptionDeleteService.deleteById(id);
	}

}
