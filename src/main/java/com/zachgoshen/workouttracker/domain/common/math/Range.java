package com.zachgoshen.workouttracker.domain.common.math;

import java.util.Optional;

public abstract class Range<T extends Number> {
	
	protected final Optional<T> lowerBound;
	protected final Optional<T> upperBound;
	
	public Range(Optional<T> lowerBound, Optional<T> upperBound) throws InvalidRangeException {
		this.lowerBound = lowerBound;
		this.upperBound = upperBound;
		
		if (!isValid()) {
			throw new InvalidRangeException();
		}
	}
	
	protected abstract boolean isValid();
	
	protected boolean isLowerBoundLessThanOrEqualToUpperBound() {
		if (!lowerBound.isPresent() || !upperBound.isPresent()) {
			return true;
		}

		float lowerBoundAsFloat = lowerBound.get().floatValue();
		float upperBoundAsFloat = upperBound.get().floatValue();
		
		return lowerBoundAsFloat <= upperBoundAsFloat;
	}

	public Optional<T> getLowerBound() {
		return lowerBound;
	}

	public Optional<T> getUpperBound() {
		return upperBound;
	}

	public boolean contains(T value) {
		return isLowerBoundLessThanOrEqualTo(value) && isUpperBoundGreaterOrEqualTo(value);
	}
	
	protected boolean isLowerBoundLessThanOrEqualTo(T value) {
		if (!lowerBound.isPresent()) {
			return true;
		}
		
		float valueAsFloat = value.floatValue();
		float lowerBoundAsFloat = lowerBound.get().floatValue();
		
		return valueAsFloat >= lowerBoundAsFloat;
	}
	
	protected boolean isUpperBoundGreaterOrEqualTo(T value) {
		if (!upperBound.isPresent()) {
			return true;
		}
		
		float valueAsFloat = value.floatValue();
		float upperBoundAsFloat = upperBound.get().floatValue();
		
		return valueAsFloat <= upperBoundAsFloat;
	}
}
