package com.zachgoshen.workouttracker.domain.workout.exercise;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.common.math.PositiveRealNumberRange;

public class WeightConstraint implements ExerciseConstraint {
	
	private final PositiveRealNumberRange range;

	public WeightConstraint(float minimumWeightAllowed) throws InvalidRangeException {
		this.range = new PositiveRealNumberRange(Optional.of(minimumWeightAllowed), Optional.empty());
	}

	public WeightConstraint(float minimumWeightAllowed, float maximumWeightAllowed) throws InvalidRangeException {
		this.range = new PositiveRealNumberRange(Optional.of(minimumWeightAllowed), Optional.of(maximumWeightAllowed));
	}

	@Override
	public boolean isSatisfiedBy(Exercise candidate) {
		Optional<Float> weightUsed = candidate.getWeightUsed();
		
		return weightUsed.isPresent() && range.contains(weightUsed.get());
	}

	public Optional<Float> getMinimumWeightAllowed() {
		return range.getLowerBound();
	}

	public Optional<Float> getMaximumWeightAllowed() {
		return range.getUpperBound();
	}

}
