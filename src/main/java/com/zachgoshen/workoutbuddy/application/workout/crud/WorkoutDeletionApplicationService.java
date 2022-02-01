package com.zachgoshen.workoutbuddy.application.workout.crud;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

@Service
public class WorkoutDeletionApplicationService {
	
	private final WorkoutRepository repository;
	
	public WorkoutDeletionApplicationService(WorkoutRepository repository) {
		this.repository = repository;
	}
	
	public void deleteById(String id) {
		repository.deleteById(id);
	}

}
