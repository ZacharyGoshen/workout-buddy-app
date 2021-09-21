package com.zachgoshen.workouttracker.domain.workout.set;

import java.util.List;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;

public class Superset extends Set {
	
	private final List<Exercise> exercises;

	public Superset(List<Exercise> exercises) {
		super();
		this.exercises = exercises;
	}

	@Override
	public Set clone() {
		// TODO Auto-generated method stub
		return null;
	}
	
	public List<Exercise> getExercises() {
		return exercises;
	}

	@Override
	public boolean wereConstraintsSatisfied() {
		return wasRestTimeConstraintSatisfied();
	}

}
