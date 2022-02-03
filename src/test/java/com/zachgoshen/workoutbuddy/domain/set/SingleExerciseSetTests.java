package com.zachgoshen.workoutbuddy.domain.set;

import static com.zachgoshen.workoutbuddy.domain.exercise.ExerciseAssertions.assertExercisesAreEqual;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;

public class SingleExerciseSetTests extends SetTests {
	
	@Test
	public void Clone_SetHasExerciseWithAllFieldsSet_ExerciseIsCopied() throws InvalidRangeException {
		Exercise exercise = buildExerciseWithAllConstraintsSetAndSatisfied();
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SingleExerciseSet copy = set.clone();
		
		assertNotSame(set.getExercise(), copy.getExercise());
		assertExercisesAreEqual(set.getExercise(), copy.getExercise());
	}
	
	@Test
	public void WereConstraintsSatisfied_AllConstraintsSetAndSatisfied_ReturnTrue() throws InvalidRangeException {
		Exercise exercise = buildExerciseWithAllConstraintsSetAndSatisfied();
		
		Set set = new SingleExerciseSet(exercise);
		set.addBoundedRestTimeConstraint(30, 60);
		set.setTimeRested(45f);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertTrue(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_ExerciseConstraintsNotSatisfied_ReturnFalse() throws InvalidRangeException {
		Exercise exercise = buildExerciseWithDescriptionOnly();
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setWeightUsed(110f);
		
		Set set = new SingleExerciseSet(exercise);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}
	
	@Override
	protected Set buildSet() {
		Exercise exercise = buildExerciseWithDescriptionOnly();
		return new SingleExerciseSet(exercise);
	}
	
	private static Exercise buildExerciseWithDescriptionOnly() {
		ExerciseDescription description = new ExerciseDescription("description");
		return new Exercise(description);
	}
	
	private static Exercise buildExerciseWithAllConstraintsSetAndSatisfied() throws InvalidRangeException {
		ExerciseDescription description = new ExerciseDescription("description");
		
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(95f);
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setRepsCompleted(9);
		exercise.addBoundedRepsConstraint(8, 10);
		exercise.setTimePerformed(45f);
		exercise.addBoundedDurationConstraint(30, 60);
		
		return exercise;
	}

}
