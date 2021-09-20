package com.zachgoshen.workouttracker.domain.workout;

import java.util.List;

public interface WorkoutRepository {

	public List<Workout> findAll();
	
}
