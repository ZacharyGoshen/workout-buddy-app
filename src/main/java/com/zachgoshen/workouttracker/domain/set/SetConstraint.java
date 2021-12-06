package com.zachgoshen.workouttracker.domain.set;

public interface SetConstraint {
	
	public boolean isSatisfiedBy(Set candidate);

}
