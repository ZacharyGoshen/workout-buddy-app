package com.zachgoshen.workouttracker.domain.workout.set;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;

public class Superset extends Set {
	
	private final List<Exercise> exercises;

	public Superset(List<Exercise> exercises) {
		super();
		
		this.exercises = exercises;
	}
	
	public Superset(Superset set) {
		super(set);
		
		exercises = set.getExercises()
			.stream()
			.map(exercise -> new Exercise(exercise))
			.collect(Collectors.toList());
	}

	@Override
	public Superset clone() {
		return new Superset(this);
	}
	
	public List<Exercise> getExercises() {
		return exercises;
	}

	@Override
	public boolean wereConstraintsSatisfied() {
		return wasRestTimeConstraintSatisfied() && wereEveryExercisesConstraintsSatisfied();
	}
	
	private boolean wereEveryExercisesConstraintsSatisfied() {
		return exercises.stream()
			.map(exercise -> exercise.wereConstraintsSatisfied())
			.reduce(true, (firstBoolean, secondBoolean) -> firstBoolean && secondBoolean);
	}

}
