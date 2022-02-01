package com.zachgoshen.workoutbuddy.domain.exercise;

import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.common.math.PositiveRealNumberRange;

public class DurationConstraint implements ExerciseConstraint {
	
	private final PositiveRealNumberRange range;

	public DurationConstraint(float minimumDurationAllowed) throws InvalidRangeException {
		this.range = new PositiveRealNumberRange(Optional.of(minimumDurationAllowed), Optional.empty());
	}

	public DurationConstraint(float minimumDurationAllowed, float maximumDurationAllowed) throws InvalidRangeException {
		this.range = new PositiveRealNumberRange(Optional.of(minimumDurationAllowed), Optional.of(maximumDurationAllowed));
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Float> timePerformed = candidate.getTimePerformed();
		
		return timePerformed.isPresent() && range.contains(timePerformed.get());
	}

	public Optional<Float> getMinimumDurationAllowed() {
		return range.getLowerBound();
	}

	public Optional<Float> getMaximumDurationAllowed() {
		return range.getUpperBound();
	}

}
