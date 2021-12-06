package com.zachgoshen.workouttracker.domain.workout.specification;

import java.util.Date;
import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.workout.Workout;

public class LatestCompletionTimeSpecification extends Specification<Workout> {
	
	private final Date latestCompletionTime;
	
	public LatestCompletionTimeSpecification(Date latestCompletionTime) {
		this.latestCompletionTime = latestCompletionTime;
	}

	@Override
	public boolean isSatisfiedBy(Workout candidate) {
		Optional<Date> timeCompletedOptional = candidate.getTimeCompleted();
		
		if (timeCompletedOptional.isPresent()) {
			Date timeCompleted = timeCompletedOptional.get();
			return timeCompleted.equals(latestCompletionTime) || timeCompleted.before(latestCompletionTime);
		} else {
			return false;
		}
	}

}
