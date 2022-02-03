package com.zachgoshen.workoutbuddy.domain.exercise.description;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class PartialNameSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_DescriptionWithMatchingName_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		PartialNameSpecification specification = new PartialNameSpecification("Bench");
		
		boolean isSatisfied = specification.isSatisfiedBy(description);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_PartialNameMatchesDescriptionBesidesCasing_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		PartialNameSpecification specification = new PartialNameSpecification("bench press");
		
		boolean isSatisfied = specification.isSatisfiedBy(description);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_PartialNameMatchesDescriptionBesidesSpacing_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		PartialNameSpecification specification = new PartialNameSpecification("BenchPress");
		
		boolean isSatisfied = specification.isSatisfiedBy(description);
		
		assertTrue(isSatisfied);
	}

	@Test
	public void IsSatisfiedBy_DescriptionWithNameThatDoesntMatch_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		PartialNameSpecification specification = new PartialNameSpecification("Squat");
		
		boolean isSatisfied = specification.isSatisfiedBy(description);
		
		assertFalse(isSatisfied);
	}

}
