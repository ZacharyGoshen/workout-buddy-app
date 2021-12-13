package com.zachgoshen.workouttracker.domain.set.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.specification.MinimumRestTimeSpecification;

public class MinimumRestTimeSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedGreaterThanMinimumRestTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(90f);
		
		MinimumRestTimeSpecification specification = new MinimumRestTimeSpecification(60f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedEqualToMinimumRestTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(90f);
		
		MinimumRestTimeSpecification specification = new MinimumRestTimeSpecification(90f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeRestedLessThanMinimumRestTime_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeRested(90f);
		
		MinimumRestTimeSpecification specification = new MinimumRestTimeSpecification(180f);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}

}