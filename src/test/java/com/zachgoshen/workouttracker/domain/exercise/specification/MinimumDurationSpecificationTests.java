package com.zachgoshen.workouttracker.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.specification.MinimumDurationSpecification;

public class MinimumDurationSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedGreaterThanMinimumDuration_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(90f);
		
		MinimumDurationSpecification specification = new MinimumDurationSpecification(60f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedEqualToMinimumDuration_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(60f);
		
		MinimumDurationSpecification specification = new MinimumDurationSpecification(60f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedLessThanMinimumDuration_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(60f);
		
		MinimumDurationSpecification specification = new MinimumDurationSpecification(90f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
