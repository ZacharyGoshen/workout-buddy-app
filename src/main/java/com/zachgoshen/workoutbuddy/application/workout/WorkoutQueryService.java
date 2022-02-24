package com.zachgoshen.workoutbuddy.application.workout;

import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

public class WorkoutQueryService implements WorkoutQueryUseCase {
	
	private final WorkoutRepository repository;
	
	public WorkoutQueryService(WorkoutRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<Workout> findAll() {
		return repository.findAll();
	}
	
	@Override
	public Optional<Workout> findById(String id) {
		return repository.findById(id);
	}

}
