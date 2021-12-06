package com.zachgoshen.workouttracker.domain.set.specification;

import java.util.Date;
import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.set.Set;

public class LatestCompletionTimeSpecification extends Specification<Set> {
	
	private final Date latestCompletionTime;
	
	public LatestCompletionTimeSpecification(Date latestCompletionTime) {
		this.latestCompletionTime = latestCompletionTime;
	}

	@Override
	public boolean isSatisfiedBy(Set candidate) {
		Optional<Date> timeCompletedOptional = candidate.getTimeCompleted();
		
		if (timeCompletedOptional.isPresent()) {
			Date timeCompleted = timeCompletedOptional.get();
			return timeCompleted.equals(latestCompletionTime) || timeCompleted.before(latestCompletionTime);
		} else {
			return false;
		}
	}

}
