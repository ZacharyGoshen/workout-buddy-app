package com.zachgoshen.workouttracker.domain.workout.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;

public class SingleExerciseSetTests {
	
	@Test
	public void Clone_TimeRestedIsSet_CopyHasSameTimeRestedValue() {
		Exercise exercise = buildExercise();
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeRested(75);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(75, copy.getTimeRested().get());
	}
	
	@Test
	public void Clone_MinimumRestTimeAllowedIsSet_CopyHasSameMinimumRestTimeAllowedValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.addBoundedRestTimeConstraint(30, 60);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(30, copy.getMinimumRestTimeAllowed().get());
	}
	
	@Test
	public void Clone_MaximumRestTimeAllowedIsSet_CopyHasSameMaximumRestTimeAllowedValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.addBoundedRestTimeConstraint(30, 60);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(60, copy.getMaximumRestTimeAllowed().get());
	}
	
	@Test
	public void Clone_WeightUsedIsSet_CopyHasSameWeightUsedValue() {
		Exercise exercise = buildExercise();
		exercise.setWeightUsed(95);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(95, copy.getExercise().getWeightUsed().get());
	}
	
	@Test
	public void Clone_MinimumWeightAllowedIsSet_CopyHasSameMinimumWeightAllowedValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(90, 100);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(90, copy.getExercise().getMinimumWeightAllowed().get());
	}
	
	@Test
	public void Clone_MaximumWeightAllowedIsSet_CopyHasSameMaximumWeightAllowedValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(90, 100);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(100, copy.getExercise().getMaximumWeightAllowed().get());
	}
	
	@Test
	public void Clone_RepsCompletedIsSet_CopyHasSameRepsCompletedValue() {
		Exercise exercise = buildExercise();
		exercise.setRepsCompleted(9);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(9, copy.getExercise().getRepsCompleted().get());
	}
	
	@Test
	public void Clone_MinimumRepsAllowedIsSet_CopyHasSameMinimumRepsAllowedValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedRepsConstraint(8, 10);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(8, copy.getExercise().getMinimumRepsAllowed().get());
	}
	
	@Test
	public void Clone_MaximumRepsAllowedIsSet_CopyHasSameMaximumRepsAllowedValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedRepsConstraint(8, 10);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(10, copy.getExercise().getMaximumRepsAllowed().get());
	}
	
	@Test
	public void Clone_TimePerformedIsSet_CopyHasSameTimePerformedValue() {
		Exercise exercise = buildExercise();
		exercise.setTimePerformed(45);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(45, copy.getExercise().getTimePerformed().get());
	}
	
	@Test
	public void Clone_MinimumDurationAllowedIsSet_CopyHasSameMinimumDurationAllowedValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedDurationConstraint(30, 60);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(30, copy.getExercise().getMinimumDurationAllowed().get());
	}
	
	@Test
	public void Clone_MaximumDurationAllowedIsSet_CopyHasSameMaximumDurationAllowedValue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedDurationConstraint(30, 60);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertEquals(60, copy.getExercise().getMaximumDurationAllowed().get());
	}
	
	@Test
	public void WereConstraintsSatisfied_NoConstraintsSets_ReturnTrue() {
		Exercise exercise = buildExercise();
		Set set = new SingleExerciseSet(exercise);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertTrue(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_AllConstraintsSetsAndSatisfied_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setWeightUsed(95);
		exercise.addBoundedRepsConstraint(8, 10);
		exercise.setRepsCompleted(9);
		exercise.addBoundedDurationConstraint(30, 60);
		exercise.setTimePerformed(45);
		Set set = new SingleExerciseSet(exercise);
		set.addBoundedRestTimeConstraint(30, 60);
		set.setTimeRested(45);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertTrue(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_TimeRestedConstraintNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		Set set = new SingleExerciseSet(exercise);
		set.addBoundedRestTimeConstraint(30, 60);
		set.setTimeRested(75);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_WeightConstraintNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setWeightUsed(110);
		Set set = new SingleExerciseSet(exercise);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_RepsConstraintNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedRepsConstraint(8, 10);
		exercise.setRepsCompleted(11);
		Set set = new SingleExerciseSet(exercise);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_DurationConstraintNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExercise();
		exercise.addBoundedDurationConstraint(30, 60);
		exercise.setTimePerformed(75);
		Set set = new SingleExerciseSet(exercise);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	private static Exercise buildExercise() {
		ExerciseDescription description = new ExerciseDescription("description");
		return new Exercise(description);
	}

}
