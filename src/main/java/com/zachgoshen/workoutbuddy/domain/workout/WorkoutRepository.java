package com.zachgoshen.workoutbuddy.domain.workout;

import java.util.List;
import java.util.Optional;

public interface WorkoutRepository {

	public List<Workout> findAll();
	
	public Optional<Workout> findById(String id);
	
	public Optional<Workout> findBySetId(String setId);
	
	public void save(Workout workout);
	
	public void deleteById(String id);
	
}
