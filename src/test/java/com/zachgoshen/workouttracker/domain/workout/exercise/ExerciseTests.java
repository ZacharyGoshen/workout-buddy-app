package com.zachgoshen.workouttracker.domain.workout.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;

public class ExerciseTests {
	
	@Test
	public void AddMinimumWeightConstraint_ValidValue_MininumWeightAllowedIsSameValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		exercise.addMinimumWeightConstraint(100);
		
		assertEquals(100, exercise.getMinimumWeightAllowed().get());
	}
	
	@Test
	public void AddMinimumWeightConstraint_ValidValue_MaximumWeightAllowedIsNotSet() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		exercise.addMinimumWeightConstraint(100);
		
		assertFalse(exercise.getMaximumWeightAllowed().isPresent());
	}
	
	@Test
	public void AddMinimumWeightConstraint_NegativeValue_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addMinimumWeightConstraint(-100));
	}
	
	@Test
	public void AddBoundedWeightConstraint_LowerBoundAndUpperBoundAreEqual_MinimumAndMaximumWeightsAllowedAreEqual() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(100, 100);

		Optional<Float> minimumWeightAllowed = exercise.getMinimumWeightAllowed();
		Optional<Float> maximumWeightAllowed = exercise.getMaximumWeightAllowed();
		
		assertEquals(100f, minimumWeightAllowed.get());
		assertEquals(100f, maximumWeightAllowed.get());
	}
	
	@Test
	public void AddBoundedWeightConstraint_NegativeLowerBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addBoundedWeightConstraint(-100, 100));
	}
	
	@Test
	public void AddBoundedWeightConstraint_LowerBoundIsGreaterThanUpperBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addBoundedWeightConstraint(100, 90));
	}
	
	@Test
	public void WasWeightConstraintSatisfied_ConstraintNotSet_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		boolean wasSatisfied = exercise.wasWeightConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasWeightConstraintSatisfied_WeightUsedNotSet_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumWeightConstraint(90);
		
		boolean wasSatisfied = exercise.wasWeightConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasWeightConstraintSatisfied_WeightIsAboveMinimum_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumWeightConstraint(90);
		exercise.setWeightUsed(95);
		
		boolean wasSatisfied = exercise.wasWeightConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasWeightConstraintSatisfied_WeightIsBelowMinimum_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumWeightConstraint(90);
		exercise.setWeightUsed(85);
		
		boolean wasSatisfied = exercise.wasWeightConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasWeightConstraintSatisfied_WeightIsWithinRange_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setWeightUsed(95);
		
		boolean wasSatisfied = exercise.wasWeightConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasWeightConstraintSatisfied_WeightIsOutsideRange_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setWeightUsed(80);
		
		boolean wasSatisfied = exercise.wasWeightConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void AddMinimumRepsConstraint_ValidValue_MininumRepsAllowedIsSameValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		exercise.addMinimumRepsConstraint(8);
		
		assertEquals(8, exercise.getMinimumRepsAllowed().get());
	}
	
	@Test
	public void AddMinimumRepsConstraint_ValidValue_MaximumRepsAllowedIsNotSet() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		exercise.addMinimumRepsConstraint(8);
		
		assertFalse(exercise.getMaximumRepsAllowed().isPresent());
	}
	
	@Test
	public void AddMinimumRepsConstraint_NegativeValue_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addMinimumRepsConstraint(-8));
	}
	
	@Test
	public void AddBoundedRepsConstraint_LowerBoundAndUpperBoundAreEqual_MinimumAndMaximumRepsAllowedAreEqual() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedRepsConstraint(8, 8);

		Optional<Integer> minimumRepsAllowed = exercise.getMinimumRepsAllowed();
		Optional<Integer> maximumRepsAllowed = exercise.getMaximumRepsAllowed();
		
		assertEquals(8, minimumRepsAllowed.get());
		assertEquals(8, maximumRepsAllowed.get());
	}
	
	@Test
	public void AddBoundedRepsConstraint_NegativeLowerBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addBoundedRepsConstraint(-8, 8));
	}
	
	@Test
	public void AddBoundedRepsConstraint_LowerBoundIsGreaterThanUpperBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addBoundedRepsConstraint(10, 8));
	}
	
	@Test
	public void WasRepsConstraintSatisfied_ConstraintNotSet_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		boolean wasSatisfied = exercise.wasRepsConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasRepsConstraintSatisfied_RepsCompletedNotSet_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumRepsConstraint(8);
		
		boolean wasSatisfied = exercise.wasRepsConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasRepsConstraintSatisfied_RepsAreAboveMinimum_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumRepsConstraint(8);
		exercise.setRepsCompleted(10);
		
		boolean wasSatisfied = exercise.wasRepsConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasRepsConstraintSatisfied_RepsAreBelowMinimum_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumRepsConstraint(8);
		exercise.setRepsCompleted(6);
		
		boolean wasSatisfied = exercise.wasRepsConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasRepsConstraintSatisfied_RepsAreWithinRange_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedRepsConstraint(8, 10);
		exercise.setRepsCompleted(9);
		
		boolean wasSatisfied = exercise.wasRepsConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasRepsConstraintSatisfied_RepsOutsideRange_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedRepsConstraint(8, 10);
		exercise.setRepsCompleted(12);
		
		boolean wasSatisfied = exercise.wasRepsConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void AddMinimumDurationConstraint_ValidValue_MininumDurationAllowedIsSameValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		exercise.addMinimumDurationConstraint(30);
		
		assertEquals(30, exercise.getMinimumDurationAllowed().get());
	}
	
	@Test
	public void AddMinimumDurationConstraint_ValidValue_MaximumDurationAllowedIsNotSet() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		exercise.addMinimumWeightConstraint(30);
		
		assertFalse(exercise.getMaximumDurationAllowed().isPresent());
	}
	
	@Test
	public void AddMinimumDurationConstraint_NegativeValue_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addMinimumDurationConstraint(-30));
	}
	
	@Test
	public void AddBoundedDurationConstraint_LowerBoundAndUpperBoundAreEqual_MinimumAndMaximumDurationsAllowedAreEqual() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedDurationConstraint(30, 30);

		Optional<Float> minimumDurationAllowed = exercise.getMinimumDurationAllowed();
		Optional<Float> maximumDurationAllowed = exercise.getMaximumDurationAllowed();
		
		assertEquals(30, minimumDurationAllowed.get());
		assertEquals(30, maximumDurationAllowed.get());
	}
	
	@Test
	public void AddBoundedDurationConstraint_NegativeLowerBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addBoundedDurationConstraint(-30, 30));
	}
	
	@Test
	public void AddBoundedDurationConstraint_LowerBoundIsGreaterThanUpperBound_ThrowsInvalidRangeException() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		assertThrows(
			InvalidRangeException.class, 
			() -> exercise.addBoundedDurationConstraint(40, 30));
	}
	
	@Test
	public void WasDurationConstraintSatisfied_ConstraintNotSet_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		boolean wasSatisfied = exercise.wasDurationConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasDurationConstraintSatisfied_TimePerformedNotSet_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumDurationConstraint(30);
		
		boolean wasSatisfied = exercise.wasDurationConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasDurationConstraintSatisfied_DurationIsAboveMinimum_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumDurationConstraint(30);
		exercise.setTimePerformed(45);
		
		boolean wasSatisfied = exercise.wasDurationConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasDurationConstraintSatisfied_DurationIsBelowMinimum_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addMinimumDurationConstraint(30);
		exercise.setTimePerformed(15);
		
		boolean wasSatisfied = exercise.wasDurationConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WasDurationConstraintSatisfied_DurationIsWithinRange_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedDurationConstraint(30, 60);
		exercise.setTimePerformed(45);
		
		boolean wasSatisfied = exercise.wasDurationConstraintSatisfied();
		
		assertTrue(wasSatisfied);
	}
	
	@Test
	public void WasDurationConstraintSatisfied_DurationIsOutsideRange_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedDurationConstraint(30, 60);
		exercise.setTimePerformed(75);
		
		boolean wasSatisfied = exercise.wasDurationConstraintSatisfied();
		
		assertFalse(wasSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_NoConstraintsSet_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		
		boolean wereSatisfied = exercise.wereConstraintsSatisfied();
		
		assertTrue(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_AllConstraintsSetAndSatisfied_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setWeightUsed(95);
		exercise.addBoundedRepsConstraint(8, 10);
		exercise.setRepsCompleted(9);
		exercise.addBoundedDurationConstraint(30, 60);
		exercise.setTimePerformed(45);
		
		boolean wereSatisfied = exercise.wereConstraintsSatisfied();
		
		assertTrue(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_WeightConstraintNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setWeightUsed(110);
		
		boolean wereSatisfied = exercise.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_RepsConstraintNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedRepsConstraint(8, 10);
		exercise.setRepsCompleted(11);
		
		boolean wereSatisfied = exercise.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_DurationConstraintNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedDurationConstraint(30, 60);
		exercise.setTimePerformed(75);
		
		boolean wereSatisfied = exercise.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	private static Exercise buildExercise() {
		ExerciseDescription description = new ExerciseDescription("description");
		return new Exercise(description);
	}

}
