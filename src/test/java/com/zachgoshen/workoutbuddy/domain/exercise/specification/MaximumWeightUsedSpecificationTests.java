package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.specification.MaximumWeightUsedSpecification;

public class MaximumWeightUsedSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedLessThanMaximumWeight_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(100f);
		
		MaximumWeightUsedSpecification specification = new MaximumWeightUsedSpecification(200f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedEqualToMaximumWeight_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(200f);
		
		MaximumWeightUsedSpecification specification = new MaximumWeightUsedSpecification(200f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedGreaterThanMaximumWeight_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(200f);
		
		MaximumWeightUsedSpecification specification = new MaximumWeightUsedSpecification(100f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
