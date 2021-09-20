package com.zachgoshen.workouttracker.domain.common.math;

import java.util.Optional;

public class PositiveRealNumberRange extends Range<Float> {

	public PositiveRealNumberRange(Optional<Float> lowerBound, Optional<Float> upperBound) throws InvalidRangeException {
		super(!lowerBound.isPresent() ? Optional.of(0f) : lowerBound, upperBound);
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
