package com.zachgoshen.workouttracker.domain.workout.session;

import java.util.List;
import java.util.Optional;

public interface WorkoutSessionRepository {
	
	public List<WorkoutSession> findAll();
	
	public Optional<WorkoutSession> findById(long id);

}
