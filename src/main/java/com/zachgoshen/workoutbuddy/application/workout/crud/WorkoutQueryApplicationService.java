package com.zachgoshen.workoutbuddy.application.workout.crud;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.application.workout.WorkoutConverter;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutDto;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

@Service
public class WorkoutQueryApplicationService {
	
	private final WorkoutRepository repository;
	
	public WorkoutQueryApplicationService(WorkoutRepository repository) {
		this.repository = repository;
	}
	
	public List<WorkoutDto> findAll() {
		return repository.findAll()
			.stream()
			.map(workout -> WorkoutConverter.toDto(workout))
			.collect(Collectors.toList());
	}
	
	public WorkoutDto findById(String id) {
		Optional<Workout> workout = repository.findById(id);
		
		return WorkoutConverter.toDto(workout.get());
	}

}
