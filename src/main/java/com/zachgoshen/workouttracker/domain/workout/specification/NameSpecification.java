package com.zachgoshen.workouttracker.domain.workout.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.workout.Workout;

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
