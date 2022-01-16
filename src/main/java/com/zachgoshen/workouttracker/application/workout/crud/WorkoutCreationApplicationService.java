package com.zachgoshen.workouttracker.application.workout.crud;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.workout.WorkoutConverter;
import com.zachgoshen.workouttracker.application.workout.WorkoutDto;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.WorkoutRepository;

@Service
public class WorkoutCreationApplicationService {
	
	private final WorkoutRepository repository;
	
	private WorkoutCreationApplicationService(WorkoutRepository repository) {
		this.repository = repository;
	}

	public String createAndReturnId(WorkoutDto dto) throws DtoConversionException, InvalidRangeException {
		Workout workout = WorkoutConverter.toEntity(dto);
		
		repository.save(workout);
		
		return workout.getId();
	}
	
}
