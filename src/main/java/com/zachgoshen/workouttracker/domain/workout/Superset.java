package com.zachgoshen.workouttracker.domain.workout;

import java.util.List;

public class Superset extends Set {
	
	private List<Exercise> exercises;

	@Override
	public Set clone() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean wereConstraintsSatisfied() {
		return wasRestTimeConstraintSatisfied();
	}

}
