package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.specification.MaximumRepsCompletedSpecification;

public class MaximumRepsCompletedSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedLessThanMaximumReps_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(6);
		
		MaximumRepsCompletedSpecification specification = new MaximumRepsCompletedSpecification(8);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedEqualToMaximumReps_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(8);
		
		MaximumRepsCompletedSpecification specification = new MaximumRepsCompletedSpecification(8);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithRepsCompletedGreaterThanMaximumReps_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(8);
		
		MaximumRepsCompletedSpecification specification = new MaximumRepsCompletedSpecification(6);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
