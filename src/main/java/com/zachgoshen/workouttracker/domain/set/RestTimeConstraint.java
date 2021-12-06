package com.zachgoshen.workouttracker.domain.set;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.common.math.PositiveRealNumberRange;

public class RestTimeConstraint implements SetConstraint {
	
	private final PositiveRealNumberRange range;

	public RestTimeConstraint(float rangeUpperBound) throws InvalidRangeException {
		this.range = new PositiveRealNumberRange(Optional.empty(), Optional.of(rangeUpperBound));
	}

	public RestTimeConstraint(float rangeLowerBound, float rangeUpperBound) throws InvalidRangeException {
		this.range = new PositiveRealNumberRange(Optional.of(rangeLowerBound), Optional.of(rangeUpperBound));
	}

	@Override
	public boolean isSatisfiedBy(Set candidate) {
		Optional<Float> timeRested = candidate.getTimeRested();
		
		return timeRested.isPresent() && range.contains(timeRested.get());
	}

	public Optional<Float> getMinimumRestTimeAllowed() {
		return range.getLowerBound();
	}

	public Optional<Float> getMaximumRestTimeAllowed() {
		return range.getUpperBound();
	}

}
