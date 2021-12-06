package com.zachgoshen.workouttracker.domain.workout.set.specification;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.workout.set.Set;

public class MinimumRestTimeSpecification extends Specification<Set> {
	
	private final float minimumRestTime;
	
	public MinimumRestTimeSpecification(float restTime) {
		this.minimumRestTime = restTime;
	}

	@Override
	public boolean isSatisfiedBy(Set candidate) {
		Optional<Float> timeRested = candidate.getTimeRested();
		
		return timeRested.isPresent() && timeRested.get() >= minimumRestTime;
	}

}
