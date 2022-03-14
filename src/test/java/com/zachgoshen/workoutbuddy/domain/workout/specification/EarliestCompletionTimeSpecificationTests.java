package com.zachgoshen.workoutbuddy.domain.workout.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.common.date.Dates;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class EarliestCompletionTimeSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_WorkoutWithTimeCompletedAfterEarliestCompletionTime_ReturnsTrue() {
		Workout workout = new Workout();
		workout.setTimeCompleted(Dates.rightNow());
		
		EarliestCompletionTimeSpecification specification = new EarliestCompletionTimeSpecification(Dates.oneHourAgo());
		
		boolean isSatisfied = specification.isSatisfiedBy(workout);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_WorkoutWithTimeCompletedEqualToEarliestCompletionTime_ReturnsTrue() {
		Date currentTime = Dates.rightNow();
		
		Workout workout = new Workout();
		workout.setTimeCompleted(currentTime);
		
		EarliestCompletionTimeSpecification specification = new EarliestCompletionTimeSpecification(currentTime);
		
		boolean isSatisfied = specification.isSatisfiedBy(workout);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_WorkoutWithTimeCompletedBeforeEarliestCompletionTime_ReturnsFalse() {
		Workout workout = new Workout();
		workout.setTimeCompleted(Dates.oneHourAgo());
		
		EarliestCompletionTimeSpecification specification = new EarliestCompletionTimeSpecification(Dates.rightNow());
		
		boolean isSatisfied = specification.isSatisfiedBy(workout);
		
		assertFalse(isSatisfied);
	}

}
