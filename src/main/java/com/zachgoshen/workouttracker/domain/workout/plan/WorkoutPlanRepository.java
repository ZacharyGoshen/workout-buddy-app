package com.zachgoshen.workouttracker.domain.workout.plan;

import java.util.List;
import java.util.Optional;

public interface WorkoutPlanRepository {
	
	public List<WorkoutPlan> findAll();
	
	public Optional<WorkoutPlan> findById(long id);

}
