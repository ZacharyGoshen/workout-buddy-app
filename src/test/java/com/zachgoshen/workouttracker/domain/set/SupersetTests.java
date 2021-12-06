package com.zachgoshen.workouttracker.domain.set;

import static com.zachgoshen.workouttracker.domain.exercise.ExerciseAssertions.assertExercisesAreEqual;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.Superset;

public class SupersetTests extends SetTests {
	
	@Test
	public void Clone_SetHasThreeExercisesWithAllFieldsSet_ExercisesAreCopied() throws InvalidRangeException {
		List<Exercise> exercises = buildExercisesWithAllConstraintsSetAndSatisfied();
		
		Superset set = new Superset(exercises);
		
		Superset copy = set.clone();
		
		assertNotSame(set.getExercises().get(0), copy.getExercises().get(0));
		assertNotSame(set.getExercises().get(1), copy.getExercises().get(1));
		assertNotSame(set.getExercises().get(2), copy.getExercises().get(2));
		assertExercisesAreEqual(set.getExercises().get(0), copy.getExercises().get(0));
		assertExercisesAreEqual(set.getExercises().get(1), copy.getExercises().get(1));
		assertExercisesAreEqual(set.getExercises().get(2), copy.getExercises().get(2));
	}
	
	@Test
	public void WereConstraintsSatisfied_AllConstraintsSetAndSatisfied_ReturnTrue() throws InvalidRangeException {
		List<Exercise> exercises = buildExercisesWithAllConstraintsSetAndSatisfied();
		
		Superset set = new Superset(exercises);
		set.addBoundedRestTimeConstraint(30, 60);
		set.setTimeRested(45f);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertTrue(wereSatisfied);
	}
	
	@Test
	public void WereConstraintsSatisfied_OneExerciseHasUnsatisfiedConstraints_ReturnFalse() throws InvalidRangeException {
		List<Exercise> exercises = buildExercisesWithAllConstraintsSetAndSatisfied();
		
		Exercise exerciseWithUnsatisfiedConstraints = buildExerciseWithUnsatisfiedConstraints();
		exercises.add(exerciseWithUnsatisfiedConstraints);
		
		Superset set = new Superset(exercises);
		
		boolean wereSatisfied = set.wereConstraintsSatisfied();
		
		assertFalse(wereSatisfied);
	}

	@Override
	protected Set buildSet() {
		List<Exercise> exercises = buildExercisesWithDescriptionOnly();
		
		return new Superset(exercises);
	}
	
	private static List<Exercise> buildExercisesWithDescriptionOnly() {
		ExerciseDescription description1 = new ExerciseDescription("description1");
		Exercise exercise1 = new Exercise(description1);

		ExerciseDescription description2 = new ExerciseDescription("description2");
		Exercise exercise2 = new Exercise(description2);

		ExerciseDescription description3 = new ExerciseDescription("description3");
		Exercise exercise3 = new Exercise(description3);

		List<Exercise> exercises = Arrays.asList(exercise1, exercise2, exercise3);
		return new ArrayList<>(exercises);
	}
	
	private static List<Exercise> buildExercisesWithAllConstraintsSetAndSatisfied() throws InvalidRangeException {
		ExerciseDescription description1 = new ExerciseDescription("description1");
		Exercise exercise1 = new Exercise(description1);
		exercise1.setWeightUsed(95f);
		exercise1.addBoundedWeightConstraint(90, 100);
		exercise1.setRepsCompleted(9);
		exercise1.addBoundedRepsConstraint(8, 10);
		exercise1.setTimePerformed(45f);
		exercise1.addBoundedDurationConstraint(30, 60);

		ExerciseDescription description2 = new ExerciseDescription("description2");
		Exercise exercise2 = new Exercise(description2);
		exercise2.setWeightUsed(50f);
		exercise2.addBoundedWeightConstraint(45, 55);
		exercise2.setRepsCompleted(7);
		exercise2.addBoundedRepsConstraint(6, 8);
		exercise2.setTimePerformed(80f);
		exercise2.addBoundedDurationConstraint(60, 90);

		ExerciseDescription description3 = new ExerciseDescription("description3");
		Exercise exercise3 = new Exercise(description3);
		exercise3.setWeightUsed(200f);
		exercise3.addBoundedWeightConstraint(200, 210);
		exercise3.setRepsCompleted(5);
		exercise3.addBoundedRepsConstraint(5, 5);
		exercise3.setTimePerformed(180f);
		exercise3.addBoundedDurationConstraint(180, 180);
		
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2, exercise3);
		return new ArrayList<>(exercises);
	}
	
	private static Exercise buildExerciseWithUnsatisfiedConstraints() throws InvalidRangeException {
		ExerciseDescription description = new ExerciseDescription("description");
		
		Exercise exercise = new Exercise(description);
		exercise.addBoundedWeightConstraint(90, 100);
		exercise.setWeightUsed(110f);
		
		return exercise;
	}

}
