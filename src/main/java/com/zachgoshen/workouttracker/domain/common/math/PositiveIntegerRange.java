package com.zachgoshen.workouttracker.domain.common.math;

import java.util.Optional;

public class PositiveIntegerRange extends Range<Integer> {

	public PositiveIntegerRange(Optional<Integer> lowerBound, Optional<Integer> upperBound) throws InvalidRangeException {
		super(!lowerBound.isPresent() ? Optional.of(0) : lowerBound, upperBound);
	}

	@Override
	protected boolean isValid() {
		return isLowerBoundValid() && isLowerBoundLessThanOrEqualToUpperBound();
	}
	
	private boolean isLowerBoundValid() {
		if (!lowerBound.isPresent()) {
			return true;
		}
		
		return lowerBound.get().floatValue() >= 0;
	}

}
