package com.zachgoshen.workoutbuddy.domain.common.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class PositiveRealNumberRangeTests extends RangeTests<Float> {
	
	@Test
	public void Constructor_LowerBoundNotSet_LowerBoundIsZero() throws InvalidRangeException {
		Optional<Float> lowerBound = Optional.empty();
		Optional<Float> upperBound = Optional.of(10f);
		PositiveRealNumberRange range = new PositiveRealNumberRange(lowerBound, upperBound);
		
		assertEquals(0, range.getLowerBound().get());
	}
	
	@Test
	public void Contains_NoBoundsSetAndValueIsPositive_ReturnsTrue() throws InvalidRangeException {
		Optional<Float> lowerBound = Optional.empty();
		Optional<Float> upperBound = Optional.empty();
		PositiveRealNumberRange range = new PositiveRealNumberRange(lowerBound, upperBound);
		
		boolean containsValue = range.contains(10f);
		
		assertTrue(containsValue);
	}
	
	@Override
	protected Range<Float> buildRangeOfFiveToTen() throws InvalidRangeException {
		Optional<Float> lowerBound = Optional.of(5f);
		Optional<Float> upperBound = Optional.of(10f);
		
		return new PositiveRealNumberRange(lowerBound, upperBound);
	}

	@Override
	protected Range<Float> buildRangeWithInvalidLowerBound() throws InvalidRangeException {
		Optional<Float> lowerBound = Optional.of(-10f);
		Optional<Float> upperBound = Optional.empty();
		
		return new PositiveRealNumberRange(lowerBound, upperBound);
	}

	@Override
	protected Range<Float> buildRangeWithInvalidUpperBound() throws InvalidRangeException {
		Optional<Float> lowerBound = Optional.empty();
		Optional<Float> upperBound = Optional.of(-10f);
		
		return new PositiveRealNumberRange(lowerBound, upperBound);
	}

	@Override
	protected Range<Float> buildRangeWithLowerBoundGreaterThanUpperBound() throws InvalidRangeException {
		Optional<Float> lowerBound = Optional.of(15f);
		Optional<Float> upperBound = Optional.of(10f);
		
		return new PositiveRealNumberRange(lowerBound, upperBound);
	}

	@Override
	protected Float zero() {
		return 0f;
	}

	@Override
	protected Float five() {
		return 5f;
	}

	@Override
	protected Float seven() {
		return 7f;
	}

	@Override
	protected Float ten() {
		return 10f;
	}

	@Override
	protected Float fifteen() {
		return 15f;
	}

}
