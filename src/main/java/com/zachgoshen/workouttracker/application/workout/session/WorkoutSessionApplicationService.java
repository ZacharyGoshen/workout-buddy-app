package com.zachgoshen.workouttracker.application.workout.session;

import java.util.List;

public interface WorkoutSessionApplicationService {
	
	public List<WorkoutSessionDto> findAll();
	
	public WorkoutSessionDto findById(long id);

}
