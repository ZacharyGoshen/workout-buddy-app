package com.zachgoshen.workouttracker.domain.exercise;

public interface ExerciseConstraint {
	
	public boolean isSatisfiedBy(Exercise candidate);

}
