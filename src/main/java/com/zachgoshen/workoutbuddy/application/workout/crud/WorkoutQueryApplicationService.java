package com.zachgoshen.workoutbuddy.application.workout.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
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
	
	public List<WorkoutDto> findAll() throws DtoConversionException {
		List<Workout> workouts = repository.findAll();
		
		List<WorkoutDto> dtos = new ArrayList<>();
		
		for (Workout workout : workouts) {
			WorkoutDto dto = WorkoutConverter.toDto(workout);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	public WorkoutDto findById(String id) throws DtoConversionException {
		Optional<Workout> workout = repository.findById(id);
		
		return WorkoutConverter.toDto(workout.get());
	}

}
