package com.zachgoshen.workoutbuddy.application.workout;

import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public interface WorkoutRepository {

	public List<Workout> findAll();
	
	public Optional<Workout> findById(String id);
	
	public Optional<Workout> findBySetId(String setId);
	
	public void save(Workout workout);
	
	public void deleteAll();
	
	public void deleteById(String id);
	
}
