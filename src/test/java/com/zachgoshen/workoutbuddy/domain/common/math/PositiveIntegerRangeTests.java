package com.zachgoshen.workoutbuddy.domain.common.math;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

public class PositiveIntegerRangeTests extends RangeTests<Integer> {
	
	@Test
	public void Constructor_LowerBoundNotSet_LowerBoundIsZero() throws InvalidRangeException {
		Optional<Integer> lowerBound = Optional.empty();
		Optional<Integer> upperBound = Optional.of(10);
		PositiveIntegerRange range = new PositiveIntegerRange(lowerBound, upperBound);
		
		assertEquals(0, range.getLowerBound().get());
	}
	
	@Test
	public void Contains_NoBoundsSetAndValueIsPositive_ReturnsTrue() throws InvalidRangeException {
		Optional<Integer> lowerBound = Optional.empty();
		Optional<Integer> upperBound = Optional.empty();
		PositiveIntegerRange range = new PositiveIntegerRange(lowerBound, upperBound);
		
		boolean containsValue = range.contains(10);
		
		assertTrue(containsValue);
	}
	
	@Override
	protected Range<Integer> buildRangeOfFiveToTen() throws InvalidRangeException {
		Optional<Integer> lowerBound = Optional.of(5);
		Optional<Integer> upperBound = Optional.of(10);
		
		return new PositiveIntegerRange(lowerBound, upperBound);
	}

	@Override
	protected Range<Integer> buildRangeWithInvalidLowerBound() throws InvalidRangeException {
		Optional<Integer> lowerBound = Optional.of(-10);
		Optional<Integer> upperBound = Optional.empty();
		
		return new PositiveIntegerRange(lowerBound, upperBound);
	}

	@Override
	protected Range<Integer> buildRangeWithInvalidUpperBound() throws InvalidRangeException {
		Optional<Integer> lowerBound = Optional.empty();
		Optional<Integer> upperBound = Optional.of(-10);
		
		return new PositiveIntegerRange(lowerBound, upperBound);
	}

	@Override
	protected Range<Integer> buildRangeWithLowerBoundGreaterThanUpperBound() throws InvalidRangeException {
		Optional<Integer> lowerBound = Optional.of(15);
		Optional<Integer> upperBound = Optional.of(10);
		
		return new PositiveIntegerRange(lowerBound, upperBound);
	}

	@Override
	protected Integer zero() {
		return 0;
	}

	@Override
	protected Integer five() {
		return 5;
	}

	@Override
	protected Integer seven() {
		return 7;
	}

	@Override
	protected Integer ten() {
		return 10;
	}

	@Override
	protected Integer fifteen() {
		return 15;
	}
	
}
