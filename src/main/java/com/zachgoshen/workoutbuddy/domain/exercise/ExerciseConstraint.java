package com.zachgoshen.workoutbuddy.domain.exercise;

public interface ExerciseConstraint {
	
	public boolean isSatisfiedBy(Exercise candidate);

}
