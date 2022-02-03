package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.specification.MinimumWeightUsedSpecification;

public class MinimumWeightUsedSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedGreaterThanMinimumWeight_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(200f);
		
		MinimumWeightUsedSpecification specification = new MinimumWeightUsedSpecification(100f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedEqualToMinimumWeight_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(200f);
		
		MinimumWeightUsedSpecification specification = new MinimumWeightUsedSpecification(200f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedLessThanMinimumWeight_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(100f);
		
		MinimumWeightUsedSpecification specification = new MinimumWeightUsedSpecification(200f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
