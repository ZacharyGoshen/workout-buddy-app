package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.specification.MinimumTimePerformedSpecification;

public class MinimumTimePerformedSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedGreaterThanMinimumDuration_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(90f);
		
		MinimumTimePerformedSpecification specification = new MinimumTimePerformedSpecification(60f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedEqualToMinimumDuration_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(60f);
		
		MinimumTimePerformedSpecification specification = new MinimumTimePerformedSpecification(60f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithTimePerformedLessThanMinimumDuration_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(60f);
		
		MinimumTimePerformedSpecification specification = new MinimumTimePerformedSpecification(90f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
