package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;

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
