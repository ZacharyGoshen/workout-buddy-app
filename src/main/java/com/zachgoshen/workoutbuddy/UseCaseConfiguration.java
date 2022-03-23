package com.zachgoshen.workoutbuddy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionCreationService;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionCreationUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDeletionService;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDeletionUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionQueryService;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionQueryUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionUpdateService;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionUpdateUseCase;
import com.zachgoshen.workoutbuddy.application.set.SetQueryService;
import com.zachgoshen.workoutbuddy.application.set.SetQueryUseCase;
import com.zachgoshen.workoutbuddy.application.set.SetRepository;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutCreationService;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutCreationUseCase;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutDeletionService;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutDeletionUseCase;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutQueryService;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutQueryUseCase;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutRepository;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutUpdateService;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutUpdateUseCase;

@Configuration
public class UseCaseConfiguration {

	private final WorkoutRepository workoutRepository;
	private final SetRepository setRepository;
	private final ExerciseDescriptionRepository exerciseDescriptionRepository;
	
	public UseCaseConfiguration(
			WorkoutRepository workoutRepository, 
			SetRepository setRepository, 
			ExerciseDescriptionRepository exerciseDescriptionRepository) {
		
		this.workoutRepository = workoutRepository;
		this.exerciseDescriptionRepository = exerciseDescriptionRepository;
		this.setRepository = setRepository;
	}
	
	@Bean
	public WorkoutQueryUseCase workoutQueryUseCase() {
		return new WorkoutQueryService(workoutRepository);
	}
	
	@Bean
	public WorkoutCreationUseCase workoutCreationUseCase() {
		return new WorkoutCreationService(workoutRepository);
	}
	
	@Bean
	public WorkoutUpdateUseCase workoutUpdateUseCase() {
		return new WorkoutUpdateService(workoutRepository);
	}
	
	@Bean
	public WorkoutDeletionUseCase workoutDeletionUseCase() {
		return new WorkoutDeletionService(workoutRepository);
	}
	
	@Bean
	public SetQueryUseCase setQueryUseCase() {
		return new SetQueryService(setRepository, workoutRepository);
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
