package com.zachgoshen.workoutbuddy.domain.set.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.specification.MaximumTimeRestedSpecification;

public class MaximumTimeRestedSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedLessThanMaximumRestTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(90f);
		
		MaximumTimeRestedSpecification specification = new MaximumTimeRestedSpecification(180f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedEqualToMaximumRestTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(90f);
		
		MaximumTimeRestedSpecification specification = new MaximumTimeRestedSpecification(90f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedGreaterThanMaximumRestTime_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(180f);
		
		MaximumTimeRestedSpecification specification = new MaximumTimeRestedSpecification(90f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}

}
