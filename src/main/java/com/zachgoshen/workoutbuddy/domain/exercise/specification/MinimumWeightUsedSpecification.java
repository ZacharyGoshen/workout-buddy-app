package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;

public class MinimumWeightUsedSpecification extends Specification<Exercise> {
	
	private final float minimumWeight;

	public MinimumWeightUsedSpecification(float minimumWeight) {
		this.minimumWeight = minimumWeight;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Float> weightUsed = candidate.getWeightUsed();
		
		return weightUsed.isPresent() && weightUsed.get() >= minimumWeight;
	}

}
