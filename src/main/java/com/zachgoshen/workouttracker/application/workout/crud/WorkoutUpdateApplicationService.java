package com.zachgoshen.workouttracker.application.workout.crud;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.set.SetConverter;
import com.zachgoshen.workouttracker.application.set.SetDto;
import com.zachgoshen.workouttracker.application.workout.NonexistentWorkoutException;
import com.zachgoshen.workouttracker.application.workout.WorkoutDto;
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
	
	public void update(String id, WorkoutDto dto) throws NonexistentWorkoutException {
		Workout workout = tryToFindWorkoutById(id);
		
		String name = dto.getName();
		workout.setName(name);
		
		Date timeCompleted = dto.getTimeCompleted();
		workout.setTimeCompleted(timeCompleted);
		
		repository.save(workout);
	}
	
	public void addSet(String workoutId, int setIndex, SetDto setDto) throws NonexistentWorkoutException, InvalidRangeException, DtoConversionException {
		Workout workout = tryToFindWorkoutById(workoutId);
		
		Set set = SetConverter.toEntity(setDto);
		workout.addSet(set, setIndex);
		
		repository.save(workout);
	}
	
	public void updateSet(String workoutId, int setIndex, SetDto updatedSetDto) throws NonexistentWorkoutException, InvalidRangeException, DtoConversionException {
		Workout workout = tryToFindWorkoutById(workoutId);
		
		workout.removeSet(setIndex);
		
		Set updatedSet = SetConverter.toEntity(updatedSetDto);
		workout.addSet(updatedSet, setIndex);
		
		repository.save(workout);
	}
	
	public void moveSet(String workoutId, int originalIndex, int destinationIndex) throws NonexistentWorkoutException {
		Workout workout = tryToFindWorkoutById(workoutId);
		
		workout.moveSet(originalIndex, destinationIndex);
		
		repository.save(workout);
	}
	
	public void removeSet(String workoutId, int setIndex) throws NonexistentWorkoutException {
		Workout workout = tryToFindWorkoutById(workoutId);
		
		workout.removeSet(setIndex);
		
		repository.save(workout);
	}
	
	private Workout tryToFindWorkoutById(String id) throws NonexistentWorkoutException {
		Optional<Workout> workoutOptional = repository.findById(id);
		
		if (workoutOptional.isPresent()) {
			return workoutOptional.get();
		} else {
			throw new NonexistentWorkoutException();
		}
	}

}
