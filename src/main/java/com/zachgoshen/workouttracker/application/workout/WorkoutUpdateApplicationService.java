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
		Optional<Workout> workoutOptional = repository.findById(workoutId);
		
		if (!workoutOptional.isPresent()) {
			throw new NonexistentWorkoutException();
		}
		
		Workout workout = workoutOptional.get();
		
		Set set = SetConverter.toEntity(setDto);
		workout.appendSet(set);
		
		repository.save(workout);
	}
	
	public void removeSet(String workoutId, int setIndex) throws NonexistentWorkoutException {
		Optional<Workout> workoutOptional = repository.findById(workoutId);
		
		if (!workoutOptional.isPresent()) {
			throw new NonexistentWorkoutException();
		}
		
		Workout workout = workoutOptional.get();
		
		workout.removeSet(setIndex);
		
		repository.save(workout);
	}

}
