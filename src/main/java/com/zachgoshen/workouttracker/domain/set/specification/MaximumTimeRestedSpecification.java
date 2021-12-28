package com.zachgoshen.workouttracker.domain.set.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.set.Set;

public class MaximumTimeRestedSpecification extends Specification<Set> {
	
	private final float maximumRestTime;
	
	public MaximumTimeRestedSpecification(float restTime) {
		this.maximumRestTime = restTime;
	}

	@Override
	public boolean isSatisfiedBy(Set candidate) {
		Optional<Float> timeRested = candidate.getTimeRested();
		
		return timeRested.isPresent() && timeRested.get() <= maximumRestTime;
	}

}
