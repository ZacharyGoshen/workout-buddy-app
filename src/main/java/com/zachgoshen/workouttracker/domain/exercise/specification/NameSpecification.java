package com.zachgoshen.workouttracker.domain.exercise.specification;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

public class NameSpecification extends Specification<Exercise> {
	
	private final String name;

	public NameSpecification(String name) {
		this.name = name;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		ExerciseDescription description = candidate.getDescription();
		return description.getName().equals(name);
	}

}
