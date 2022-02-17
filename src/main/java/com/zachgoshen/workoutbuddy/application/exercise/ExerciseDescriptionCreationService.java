package com.zachgoshen.workoutbuddy.application.exercise;

import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;

public class ExerciseDescriptionCreationService implements ExerciseDescriptionCreationUseCase {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionCreationService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void create(ExerciseDescription description) {
		repository.save(description);
	}

}
