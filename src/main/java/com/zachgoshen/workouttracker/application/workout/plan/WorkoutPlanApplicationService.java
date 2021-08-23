package com.zachgoshen.workouttracker.application.workout.plan;

import java.util.List;

public interface WorkoutPlanApplicationService {

	public List<WorkoutPlanDto> findAll();
	
	public WorkoutPlanDto findById(long id);
	
}
