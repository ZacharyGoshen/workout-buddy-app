package com.zachgoshen.workouttracker.domain.workout.set;

public interface SetConstraint {
	
	public boolean isSatisfiedBy(Set candidate);

}
