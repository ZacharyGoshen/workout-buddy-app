package com.zachgoshen.workouttracker.domain.exercise.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

public class NameSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ExerciseWithMatchingName_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		
		NameSpecification specification = new NameSpecification("Plank");
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_ExerciseWithNotMatchingName_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		
		NameSpecification specification = new NameSpecification("Crunch");
		
		boolean isSatisfied = specification.isSatisfiedBy(exercise);
		
		assertFalse(isSatisfied);
	}

}
