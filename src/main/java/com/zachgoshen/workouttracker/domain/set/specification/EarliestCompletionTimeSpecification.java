package com.zachgoshen.workouttracker.domain.set.specification;

import java.util.Date;
import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.set.Set;

public class EarliestCompletionTimeSpecification extends Specification<Set> {
	
	private final Date earliestCompletionTime;
	
	public EarliestCompletionTimeSpecification(Date earliestCompletionTime) {
		this.earliestCompletionTime = earliestCompletionTime;
	}

	@Override
	public boolean isSatisfiedBy(Set candidate) {
		Optional<Date> timeCompletedOptional = candidate.getTimeCompleted();
		
		if (timeCompletedOptional.isPresent()) {
			Date timeCompleted = timeCompletedOptional.get();
			return timeCompleted.equals(earliestCompletionTime) || timeCompleted.after(earliestCompletionTime);
		} else {
			return false;
		}
	}

}
