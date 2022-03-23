package com.zachgoshen.workoutbuddy.application.workout;

public class WorkoutDeletionService implements WorkoutDeletionUseCase {
	
	private final WorkoutRepository repository;
	
	public WorkoutDeletionService(WorkoutRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void deleteById(String id) {
		repository.deleteById(id);
	}

}
