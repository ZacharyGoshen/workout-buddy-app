package com.zachgoshen.workouttracker.domain.workout.set.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.workout.set.Set;
import com.zachgoshen.workouttracker.domain.workout.set.SingleExerciseSet;

public class MaximumRestTimeSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedLessThanMaximumRestTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(90f);
		
		MaximumRestTimeSpecification specification = new MaximumRestTimeSpecification(180f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedEqualToMaximumRestTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(90f);
		
		MaximumRestTimeSpecification specification = new MaximumRestTimeSpecification(90f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedGreaterThanMaximumRestTime_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(180f);
		
		MaximumRestTimeSpecification specification = new MaximumRestTimeSpecification(90f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}

}
