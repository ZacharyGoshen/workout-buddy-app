package com.zachgoshen.workouttracker.application.workout;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.WorkoutRepository;

@Service
public class WorkoutQueryApplicationService {
	
	private final WorkoutRepository repository;
	
	public WorkoutQueryApplicationService(WorkoutRepository repository) {
		this.repository = repository;
	}
	
	public List<WorkoutDto> findAll() {
		return repository.findAll()
			.stream()
			.map(workout -> WorkoutDtoAssembler.assemble(workout))
			.collect(Collectors.toList());
	}
	
	public WorkoutDto findById(String id) {
		Optional<Workout> workout = repository.findById(id);
		
		return WorkoutDtoAssembler.assemble(workout.get());
	}

}
