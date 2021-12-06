package com.zachgoshen.workouttracker.domain.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;

public class MinimumRepsSpecification extends Specification<Exercise> {
	
	private final int minimumReps;

	public MinimumRepsSpecification(int minimumReps) {
		this.minimumReps = minimumReps;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Integer> repsCompleted = candidate.getRepsCompleted();
		
		return repsCompleted.isPresent() && repsCompleted.get() >= minimumReps;
	}

}
