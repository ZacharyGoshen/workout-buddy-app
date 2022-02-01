package com.zachgoshen.workoutbuddy.domain.workout.specification;

import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class NameSpecification extends Specification<Workout> {
	
	private final String name;

	public NameSpecification(String name) {
		this.name = name;
	}

	@Override
	public boolean isSatisfiedBy(Workout candidate) {
		Optional<String> candidateName = candidate.getName();
		
		return candidateName.isPresent() && candidateName.get().equals(name);
	}

}
