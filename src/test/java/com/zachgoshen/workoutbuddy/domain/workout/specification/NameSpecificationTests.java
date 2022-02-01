package com.zachgoshen.workoutbuddy.domain.workout.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.specification.NameSpecification;

public class NameSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_WorkoutWithMatchingName_ReturnsTrue() {
		Workout workout = new Workout();
		workout.setName("Push Day");
		
		NameSpecification specification = new NameSpecification("Push Day");
		
		boolean isSatisfied = specification.isSatisfiedBy(workout);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_WorkoutWithNotMatchingName_ReturnsFalse() {
		Workout workout = new Workout();
		workout.setName("Push Day");
		
		NameSpecification specification = new NameSpecification("Leg Day");
		
		boolean isSatisfied = specification.isSatisfiedBy(workout);
		
		assertFalse(isSatisfied);
	}

}
