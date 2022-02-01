package com.zachgoshen.workoutbuddy.domain.common.math;

import java.util.Optional;

public class PositiveIntegerRange extends Range<Integer> {

	public PositiveIntegerRange(Optional<Integer> lowerBound, Optional<Integer> upperBound) throws InvalidRangeException {
		super(!lowerBound.isPresent() ? Optional.of(0) : lowerBound, upperBound);
	}

	@Override
	protected boolean isLowerBoundValid() {
		if (!lowerBound.isPresent()) {
			return true;
		}
		
		return lowerBound.get().intValue() >= 0;
	}

	@Override
	protected boolean isUpperBoundValid() {
		if (!upperBound.isPresent()) {
			return true;
		}
		
		return upperBound.get().intValue() >= 0;
	}

}
