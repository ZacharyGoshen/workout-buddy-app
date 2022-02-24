package com.zachgoshen.workoutbuddy.application.workout;

import java.util.Date;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

public class WorkoutUpdateService implements WorkoutUpdateUseCase {
	
	private final WorkoutRepository repository;
	
	public WorkoutUpdateService(WorkoutRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void update(String id, WorkoutUpdate update) throws NonexistentWorkoutException {
		Workout workout = tryToFindWorkoutById(id);
		
		Optional<String> name = update.getName();
		if (name.isPresent()) {
			workout.setName(name.get());
		}
		
		Optional<Date> timeCompleted = update.getTimeCompleted();
		if (timeCompleted.isPresent()) {
			workout.setTimeCompleted(timeCompleted.get());
		}
		
		repository.save(workout);
	}
	
	@Override
	public void addSet(String workoutId, int setIndex, Set set) throws NonexistentWorkoutException {
		Workout workout = tryToFindWorkoutById(workoutId);
		workout.addSet(set, setIndex);
		
		repository.save(workout);
	}
	
	@Override
	public void replaceSet(String workoutId, int setIndex, Set set) throws NonexistentWorkoutException {
		Workout workout = tryToFindWorkoutById(workoutId);
		workout.removeSet(setIndex);
		workout.addSet(set, setIndex);
		
		repository.save(workout);
	}
	
	@Override
	public void moveSet(String workoutId, int originalIndex, int destinationIndex) throws NonexistentWorkoutException {
		Workout workout = tryToFindWorkoutById(workoutId);
		
		workout.moveSet(originalIndex, destinationIndex);
		
		repository.save(workout);
	}
	
	@Override
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
