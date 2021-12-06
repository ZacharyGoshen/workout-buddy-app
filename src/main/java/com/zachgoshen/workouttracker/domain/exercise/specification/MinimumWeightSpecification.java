package com.zachgoshen.workouttracker.domain.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;

public class MinimumWeightSpecification extends Specification<Exercise> {
	
	private final float minimumWeight;

	public MinimumWeightSpecification(float minimumWeight) {
		this.minimumWeight = minimumWeight;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Float> weightUsed = candidate.getWeightUsed();
		
		return weightUsed.isPresent() && weightUsed.get() >= minimumWeight;
	}

}
