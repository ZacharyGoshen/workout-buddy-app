package com.zachgoshen.workouttracker.application.exercise;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescriptionRepository;

@RestController
@RequestMapping("/api/exercise-descriptions")
public class ExerciseDescriptionController {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionController(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("")
	public List<String> findAll() {
		return repository.findAll()
			.stream()
			.map(exerciseDescription -> exerciseDescription.getName())
			.collect(Collectors.toList());
	}

}
