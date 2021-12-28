package com.zachgoshen.workouttracker.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

public class MinimumRepsCompletedSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedGreaterThanMinimumReps_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(8);
		
		MinimumRepsCompletedSpecification specification = new MinimumRepsCompletedSpecification(6);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedEqualToMinimumReps_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(8);
		
		MinimumRepsCompletedSpecification specification = new MinimumRepsCompletedSpecification(8);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedLessThanMinimumReps_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(6);
		
		MinimumRepsCompletedSpecification specification = new MinimumRepsCompletedSpecification(8);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
