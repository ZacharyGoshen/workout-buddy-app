package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;

public class MinimumRepsCompletedSpecification extends Specification<Exercise> {
	
	private final int minimumReps;

	public MinimumRepsCompletedSpecification(int minimumReps) {
		this.minimumReps = minimumReps;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Integer> repsCompleted = candidate.getRepsCompleted();
		
		return repsCompleted.isPresent() && repsCompleted.get() >= minimumReps;
	}

}
