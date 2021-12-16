package com.zachgoshen.workouttracker.application.workout;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.NonexistentWorkoutException;
import com.zachgoshen.workouttracker.application.set.SetConverter;
import com.zachgoshen.workouttracker.application.set.SetDto;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.WorkoutRepository;

@Service
public class WorkoutUpdateApplicationService {
	
	private final WorkoutRepository repository;
	
	public WorkoutUpdateApplicationService(WorkoutRepository repository) {
		this.repository = repository;
	}
	
	public void addSet(String workoutId, SetDto setDto) throws NonexistentWorkoutException, InvalidRangeException, DtoConversionException {
		Optional<Workout> workout = repository.findById(workoutId);
		
		if (workout.isPresent()) {
			Set set = SetConverter.toEntity(setDto);
			workout.get().appendSet(set);
		} else {
			throw new NonexistentWorkoutException();
		}
	}

}
