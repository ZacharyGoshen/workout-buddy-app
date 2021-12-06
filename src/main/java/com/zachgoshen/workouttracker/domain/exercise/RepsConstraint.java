package com.zachgoshen.workouttracker.domain.exercise;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.common.math.PositiveIntegerRange;

public class RepsConstraint implements ExerciseConstraint {
	
	private final PositiveIntegerRange range;

	public RepsConstraint(int minimumRepsAllowed) throws InvalidRangeException {
		this.range = new PositiveIntegerRange(Optional.of(minimumRepsAllowed), Optional.empty());
	}

	public RepsConstraint(int minimumRepsAllowed, int maximumRepsAllowed) throws InvalidRangeException {
		this.range = new PositiveIntegerRange(Optional.of(minimumRepsAllowed), Optional.of(maximumRepsAllowed));
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Integer> repsCompleted = candidate.getRepsCompleted();
		
		return repsCompleted.isPresent() && range.contains(repsCompleted.get());
	}

	public Optional<Integer> getMinimumRepsAllowed() {
		return range.getLowerBound();
	}

	public Optional<Integer> getMaximumRepsAllowed() {
		return range.getUpperBound();
	}

}
