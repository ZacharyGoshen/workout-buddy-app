package com.zachgoshen.workoutbuddy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionCreationService;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionCreationUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDeletionService;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDeletionUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionQueryService;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionQueryUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionUpdateService;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionUpdateUseCase;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.set.SetRepository;

@Configuration
public class UseCaseConfiguration {
	
	private final ExerciseDescriptionRepository exerciseDescriptionRepository;
	private final SetRepository setRepository;
	
	public UseCaseConfiguration(ExerciseDescriptionRepository exerciseDescriptionRepository, SetRepository setRepository) {
		this.exerciseDescriptionRepository = exerciseDescriptionRepository;
		this.setRepository = setRepository;
	}
	
	@Bean
	public ExerciseDescriptionQueryUseCase exerciseDescriptionQueryUseCase() {
		return new ExerciseDescriptionQueryService(exerciseDescriptionRepository);
	}
	
	@Bean
	public ExerciseDescriptionCreationUseCase exerciseDescriptionCreationUseCase() {
		return new ExerciseDescriptionCreationService(exerciseDescriptionRepository);
	}
	
	@Bean
	public ExerciseDescriptionUpdateUseCase exerciseDescriptionUpdateUseCase() {
		return new ExerciseDescriptionUpdateService(exerciseDescriptionRepository);
	}
	
	@Bean
	public ExerciseDescriptionDeletionUseCase exerciseDescriptionDeletionUseCase() {
		return new ExerciseDescriptionDeletionService(exerciseDescriptionRepository, setRepository);
	}

}
