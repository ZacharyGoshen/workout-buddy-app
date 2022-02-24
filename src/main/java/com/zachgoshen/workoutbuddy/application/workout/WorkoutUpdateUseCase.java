package com.zachgoshen.workoutbuddy.application.workout;

import com.zachgoshen.workoutbuddy.domain.set.Set;

public interface WorkoutUpdateUseCase {
	
	public void update(String id, WorkoutUpdate update) throws NonexistentWorkoutException;
	
	public void addSet(String workoutId, int setIndex, Set set) throws NonexistentWorkoutException;
	
	public void replaceSet(String workoutId, int setIndex, Set updatedSet) throws NonexistentWorkoutException;
	
	public void moveSet(String workoutId, int originalIndex, int destinationIndex) throws NonexistentWorkoutException;
	
	public void removeSet(String workoutId, int setIndex) throws NonexistentWorkoutException;

}
