package com.zachgoshen.workouttracker.application.exercise;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/exercise-descriptions")
public class ExerciseDescriptionController {
	
	private final ExerciseDescriptionQueryApplicationService exerciseDescriptionQueryService;
	
	public ExerciseDescriptionController(ExerciseDescriptionQueryApplicationService exerciseDescriptionQueryService) {
		this.exerciseDescriptionQueryService = exerciseDescriptionQueryService;
	}
	
	@GetMapping("")
	public List<ExerciseDescriptionDto> findAll() {
		return exerciseDescriptionQueryService.findAll();
	}

}
