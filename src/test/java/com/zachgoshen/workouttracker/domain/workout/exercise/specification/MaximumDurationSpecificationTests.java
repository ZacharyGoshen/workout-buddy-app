package com.zachgoshen.workouttracker.domain.workout.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;

public class MaximumDurationSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedLessThanMaximumDuration_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(60f);
		
		MaximumDurationSpecification specification = new MaximumDurationSpecification(90f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedEqualToMaximumDuration_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(60f);
		
		MaximumDurationSpecification specification = new MaximumDurationSpecification(60f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedGreaterThanMaximumDuration_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(90f);
		
		MaximumDurationSpecification specification = new MaximumDurationSpecification(60f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
