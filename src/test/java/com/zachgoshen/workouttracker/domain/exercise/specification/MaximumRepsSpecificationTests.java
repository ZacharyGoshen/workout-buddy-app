package com.zachgoshen.workouttracker.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.specification.MaximumRepsSpecification;

public class MaximumRepsSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedLessThanMaximumReps_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(6);
		
		MaximumRepsSpecification specification = new MaximumRepsSpecification(8);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedEqualToMaximumReps_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(8);
		
		MaximumRepsSpecification specification = new MaximumRepsSpecification(8);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedGreaterThanMaximumReps_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(8);
		
		MaximumRepsSpecification specification = new MaximumRepsSpecification(6);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
