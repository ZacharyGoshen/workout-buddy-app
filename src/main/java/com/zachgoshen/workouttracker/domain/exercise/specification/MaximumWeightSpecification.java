package com.zachgoshen.workouttracker.domain.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;

public class MaximumWeightSpecification extends Specification<Exercise> {
	
	private final float maximumWeight;

	public MaximumWeightSpecification(float maximumWeight) {
		this.maximumWeight = maximumWeight;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Float> weightUsed = candidate.getWeightUsed();
		
		return weightUsed.isPresent() && weightUsed.get() <= maximumWeight;
	}

}
