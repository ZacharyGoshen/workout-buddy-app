package com.zachgoshen.workouttracker.domain.workout.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;

public class MaximumRepsSpecification extends Specification<Exercise> {
	
	private final int maximumReps;

	public MaximumRepsSpecification(int maximumReps) {
		this.maximumReps = maximumReps;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Integer> repsCompleted = candidate.getRepsCompleted();
		
		return repsCompleted.isPresent() && repsCompleted.get() <= maximumReps;
	}

}
