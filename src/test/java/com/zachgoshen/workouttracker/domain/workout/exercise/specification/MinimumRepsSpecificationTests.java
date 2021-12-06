package com.zachgoshen.workouttracker.domain.workout.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;

public class MinimumRepsSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedGreaterThanMinimumReps_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(8);
		
		MinimumRepsSpecification specification = new MinimumRepsSpecification(6);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedEqualToMinimumReps_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(8);
		
		MinimumRepsSpecification specification = new MinimumRepsSpecification(8);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedLessThanMinimumReps_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(6);
		
		MinimumRepsSpecification specification = new MinimumRepsSpecification(8);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
