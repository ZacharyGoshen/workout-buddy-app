package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;

public class MinimumTimePerformedSpecification extends Specification<Exercise> {
	
	private final float minimumDuration;

	public MinimumTimePerformedSpecification(float minimumDuration) {
		this.minimumDuration = minimumDuration;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Float> timePerformed = candidate.getTimePerformed();
		
		return timePerformed.isPresent() && timePerformed.get() >= minimumDuration;
	}

}
