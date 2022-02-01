package com.zachgoshen.workoutbuddy.domain.set;

public interface SetConstraint {
	
	public boolean isSatisfiedBy(Set candidate);

}
