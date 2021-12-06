package com.zachgoshen.workouttracker.domain.common.math;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public abstract class RangeTests<T extends Number> {

	@Test
	public void Constructor_RangeIsValid_DoesntThrowException() throws InvalidRangeException {
		assertDoesNotThrow(() -> buildRangeOfFiveToTen());
	}

	@Test
	public void Constructor_InvalidLowerBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		assertThrows(InvalidRangeException.class, () -> buildRangeWithInvalidLowerBound());
	}

	@Test
	public void Constructor_InvalidUpperBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		assertThrows(InvalidRangeException.class, () -> buildRangeWithInvalidUpperBound());
	}

	@Test
	public void Constructor_LowerBoundGreaterThanUpperBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		assertThrows(InvalidRangeException.class, () -> buildRangeWithLowerBoundGreaterThanUpperBound());
	}
	
	@Test
	public void Contains_ValueWithinRange_ReturnsTrue() throws InvalidRangeException {
		Range<T> range = buildRangeOfFiveToTen();
		
		boolean containsValue = range.contains(seven());
		
		assertTrue(containsValue);
	}
	
	@Test
	public void Contains_ValueEqualsLowerBound_ReturnsTrue() throws InvalidRangeException {
		Range<T> range = buildRangeOfFiveToTen();
		
		boolean containsValue = range.contains(five());
		
		assertTrue(containsValue);
	}
	
	@Test
	public void Contains_ValueEqualsUpperBound_ReturnsTrue() throws InvalidRangeException {
		Range<T> range = buildRangeOfFiveToTen();
		
		boolean containsValue = range.contains(ten());
		
		assertTrue(containsValue);
	}
	
	@Test
	public void Contains_ValueLessThanLowerBound_ReturnsFalse() throws InvalidRangeException {
		Range<T> range = buildRangeOfFiveToTen();
		
		boolean containsValue = range.contains(zero());
		
		assertFalse(containsValue);
	}
	
	@Test
	public void Contains_ValueGreaterThanUpperBound_ReturnsFalse() throws InvalidRangeException {
		Range<T> range = buildRangeOfFiveToTen();
		
		boolean containsValue = range.contains(fifteen());
		
		assertFalse(containsValue);
	}
	
	protected abstract Range<T> buildRangeOfFiveToTen() throws InvalidRangeException;
	
	protected abstract Range<T> buildRangeWithInvalidLowerBound() throws InvalidRangeException;
	
	protected abstract Range<T> buildRangeWithInvalidUpperBound() throws InvalidRangeException;
	
	protected abstract Range<T> buildRangeWithLowerBoundGreaterThanUpperBound() throws InvalidRangeException;
	
	protected abstract T zero();
	
	protected abstract T five();
	
	protected abstract T seven();
	
	protected abstract T ten();
	
	protected abstract T fifteen();

}
