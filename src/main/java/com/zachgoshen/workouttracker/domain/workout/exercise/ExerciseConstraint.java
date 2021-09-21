package com.zachgoshen.workouttracker.domain.workout.exercise;

public interface ExerciseConstraint {
	
	public boolean isSatisfiedBy(Exercise candidate);

}
