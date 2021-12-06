package com.zachgoshen.workouttracker.domain.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;

public class MaximumDurationSpecification extends Specification<Exercise> {
	
	private final float maximumDuration;

	public MaximumDurationSpecification(float maximumDuration) {
		this.maximumDuration = maximumDuration;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Float> timePerformed = candidate.getTimePerformed();
		
		return timePerformed.isPresent() && timePerformed.get() <= maximumDuration;
	}

}
