package com.zachgoshen.workoutbuddy.domain.workout.specification;

import java.util.Date;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class EarliestCompletionTimeSpecification extends Specification<Workout> {
	
	private final Date earliestCompletionTime;
	
	public EarliestCompletionTimeSpecification(Date earliestCompletionTime) {
		this.earliestCompletionTime = earliestCompletionTime;
	}

	@Override
	public boolean isSatisfiedBy(Workout candidate) {
		Optional<Date> timeCompletedOptional = candidate.getTimeCompleted();
		
		if (timeCompletedOptional.isPresent()) {
			Date timeCompleted = timeCompletedOptional.get();
			return timeCompleted.equals(earliestCompletionTime) || timeCompleted.after(earliestCompletionTime);
		} else {
			return false;
		}
	}

}
