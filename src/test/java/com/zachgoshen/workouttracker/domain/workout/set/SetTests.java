package com.zachgoshen.workouttracker.domain.workout.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;

public class SetTests {
	
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
		set.setTimeRested(45);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_TimeRestedIsAboveMaximum_ReturnFalse() throws InvalidRangeException {
		Set set = buildSet();
		set.addMaximumRestTimeConstraint(60);
		set.setTimeRested(75);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_TimeRestedIsWithinRange_ReturnTrue() throws InvalidRangeException {
		Set set = buildSet();
		set.addBoundedRestTimeConstraint(30, 60);
		set.setTimeRested(45);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasRestTimeConstraintSatisfied_TimeRestedIsOutsideRange_ReturnFalse() throws InvalidRangeException {
		Set set = buildSet();
		set.addBoundedRestTimeConstraint(0, 60);
		set.setTimeRested(90);
		
		boolean wasSatisfied = set.wasRestTimeConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	private static Set buildSet() {
		ExerciseDescription description = new ExerciseDescription("description");
		Exercise exercise = new Exercise(description);
		return new SingleExerciseSet(exercise);
	}

}
