package com.zachgoshen.workouttracker.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.specification.MinimumWeightSpecification;

public class MinimumWeightSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedGreaterThanMinimumWeight_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(200f);
		
		MinimumWeightSpecification specification = new MinimumWeightSpecification(100f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedEqualToMinimumWeight_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(200f);
		
		MinimumWeightSpecification specification = new MinimumWeightSpecification(200f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithWeightUsedLessThanMinimumWeight_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(100f);
		
		MinimumWeightSpecification specification = new MinimumWeightSpecification(200f);
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
