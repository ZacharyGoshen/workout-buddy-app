package com.zachgoshen.workoutbuddy.application.workout;

import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public interface WorkoutQueryUseCase {
	
	public List<Workout> findAll();
	
	public Optional<Workout> findById(String id);

}
