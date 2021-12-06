package com.zachgoshen.workouttracker.domain.workout.exercise.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;

public class MinimumDurationSpecification extends Specification<Exercise> {
	
	private final float minimumDuration;

	public MinimumDurationSpecification(float minimumDuration) {
		this.minimumDuration = minimumDuration;
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Float> timePerformed = candidate.getTimePerformed();
		
		return timePerformed.isPresent() && timePerformed.get() >= minimumDuration;
	}

}
