package com.zachgoshen.workoutbuddy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zachgoshen.workoutbuddy.data.exercise.MockExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.data.set.MockSetRepository;
import com.zachgoshen.workoutbuddy.data.workout.MockWorkoutRepository;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.set.SetRepository;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

@Configuration
public class RepositoryConfiguration {
	
	@Bean
	public WorkoutRepository workoutRepository() throws InvalidRangeException {
		return new MockWorkoutRepository(exerciseDescriptionRepository());
	}
	
	@Bean
	public SetRepository setRepository() throws InvalidRangeException {
		return new MockSetRepository(workoutRepository());
	}
	
	@Bean
	public ExerciseDescriptionRepository exerciseDescriptionRepository() {
		return new MockExerciseDescriptionRepository();
	}

}
