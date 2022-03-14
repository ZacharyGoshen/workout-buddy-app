package com.zachgoshen.workoutbuddy.domain.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;

public abstract class SetTests {
	
	@Test
	public void Clone_TimeRestedHasNotBeenSet_TimeRestedValueOfCopyHasNotBeenSet() {
		Set set = buildSet();
		
		Set copy = set.clone();
		
		assertFalse(copy.getTimeRested().isPresent());
	}
	
	@Test
	public void Clone_TimeRestedIsSet_CopyHasSameTimeRested() {
		Set set = buildSet();
		set.setTimeRested(75f);
		
		Set copy = set.clone();
		
		assertEquals(75, copy.getTimeRested().get());
	}
	
	@Test
	public void Clone_MinimumRestTimeAllowedHasNotBeenSet_MinimumRestTimeAllowedOfCopyHasNotBeenSet() {
		Set set = buildSet();
		
		Set copy = set.clone();
		
		assertFalse(copy.getMinimumRestTimeAllowed().isPresent());
	}
	
	@Test
	public void Clone_MinimumRestTimeAllowedIsSet_CopyHasSameMinimumRestTimeAllowed() throws InvalidRangeException {
		Set set = buildSet();
		set.addBoundedRestTimeConstraint(30, 60);
		
		Set copy = set.clone();
		
		assertEquals(30, copy.getMinimumRestTimeAllowed().get());
	}
	
	@Test
	public void Clone_MaximumRestTimeAllowedHasNotBeenSet_MaximumRestTimeAllowedOfCopyHasNotBeenSet() {
		Set set = buildSet();
		
		Set copy = set.clone();
		
		assertFalse(copy.getMaximumRestTimeAllowed().isPresent());
	}
	
	@Test
	public void Clone_MaximumRestTimeAllowedIsSet_CopyHasSameMaximumRestTimeAllowed() throws InvalidRangeException {
		Set set = buildSet();
		set.addBoundedRestTimeConstraint(30, 60);
		
		Set copy = set.clone();
		
		assertEquals(60, copy.getMaximumRestTimeAllowed().get());
	}
	
	@Test
	public void AddMaximumRestTimeConstraint_ValidValue_MaximumRestTimeAllowedIsSameValue() throws InvalidRangeException {
		Set set = buildSet();
		set.addMaximumRestTimeConstraint(60);

		Optional<Float> maximumRestTimeAllowed = set.getMaximumRestTimeAllowed();
		
		assertEquals(60f, maximumRestTimeAllowed.get());
	}
	
	@Test
	public void AddMaximumRestTimeConstraint_ValidValue_MinimumRestTimeAllowedIsZero() throws InvalidRangeException {
		Set set = buildSet();
		set.addMaximumRestTimeConstraint(60);

		Optional<Float> minimumRestTimeAllowed = set.getMinimumRestTimeAllowed();
		
		assertEquals(0, minimumRestTimeAllowed.get());
	}
	
	@Test
	public void AddMaximumRestTimeConstraint_NegativeValue_ThrowsInvalidRangeException() throws InvalidRangeException {
		Set set = buildSet();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> set.addMaximumRestTimeConstraint(-60));
	}
	
	@Test
	public void AddBoundedRestTimeConstraint_LowerBoundAndUpperBoundAreEqual_MinimumAndMaximumRestTimesAllowedAreEqual() throws InvalidRangeException {
		Set set = buildSet();
		set.addBoundedRestTimeConstraint(60, 60);

		Optional<Float> minimumRestTimeAllowed = set.getMinimumRestTimeAllowed();
		Optional<Float> maximumRestTimeAllowed = set.getMaximumRestTimeAllowed();
		
		assertEquals(60f, minimumRestTimeAllowed.get());
		assertEquals(60f, maximumRestTimeAllowed.get());
	}
	
	@Test
	public void AddBoundedRestTimeConstraint_NegativeLowerBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		Set set = buildSet();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> set.addBoundedRestTimeConstraint(-60, 60));
	}
	
	@Test
	public void AddBoundedRestTimeConstraint_LowerBoundIsGreaterThanUpperBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		Set set = buildSet();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> set.addBoundedRestTimeConstraint(60, 0));
	}
	
	@Test
	public void WereConstraintsSatisfied_NoConstraintsSets_ReturnTrue() throws InvalidRangeException {
		Set set = buildSet();
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertTrue(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_TimeRestedConstraintNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Set set = buildSet();
		set.addBoundedRestTimeConstraint(30, 60);
		set.setTimeRested(75f);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_ConstraintNotSet_ReturnTrue() throws InvalidRangeException {
		Set set = buildSet();
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_TimeRestedNotSet_ReturnFalse() throws InvalidRangeException {
		Set set = buildSet();
		set.addMaximumRestTimeConstraint(60);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_TimeRestedIsBelowMaximum_ReturnTrue() throws InvalidRangeException {
		Set set = buildSet();
		set.addMaximumRestTimeConstraint(60);
		set.setTimeRested(45f);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_TimeRestedIsAboveMaximum_ReturnFalse() throws InvalidRangeException {
		Set set = buildSet();
		set.addMaximumRestTimeConstraint(60);
		set.setTimeRested(75f);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_TimeRestedIsWithinRange_ReturnTrue() throws InvalidRangeException {
		Set set = buildSet();
		set.addBoundedRestTimeConstraint(30, 60);
		set.setTimeRested(45f);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_TimeRestedIsOutsideRange_ReturnFalse() throws InvalidRangeException {
		Set set = buildSet();
		set.addBoundedRestTimeConstraint(0, 60);
		set.setTimeRested(90f);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	protected abstract Set buildSet();

}
