package com.zachgoshen.workoutbuddy.domain.set;

import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;

public class SingleExerciseSet extends Set {
	
	private final Exercise exercise;

	public SingleExerciseSet(Exercise exercise) {
		super();
		
		this.exercise = exercise;
	}
	
	public SingleExerciseSet(SingleExerciseSet set) {
		super(set);
		
		exercise = new Exercise(set.getExercise());
	}

	@Override
	public SingleExerciseSet clone() {
		return new SingleExerciseSet(this);
	}

	public Exercise getExercise() {
		return exercise;
	}

	@Override
	public boolean wereConstraintsSatisfied() {
		return wasRestTimeConstraintSatisfied() && exercise.wereConstraintsSatisfied();
	}

}
