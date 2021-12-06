package com.zachgoshen.workouttracker.domain.workout.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.common.date.Dates;
import com.zachgoshen.workouttracker.domain.workout.Workout;

public class LatestCompletionTimeSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_WorkoutWithTimeCompletedBeforeLatestCompletionTime_ReturnsTrue() {
		Workout workout = new Workout();
		workout.setTimeCompleted(Dates.oneHourAgo());
		
		LatestCompletionTimeSpecification specification = new LatestCompletionTimeSpecification(Dates.rightNow());
		
		boolean isSatisfied = specification.isSatisfiedBy(workout);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_WorkoutWithTimeCompletedEqualToLatestCompletionTime_ReturnsTrue() {
		Date currentTime = Dates.rightNow();
		
		Workout workout = new Workout();
		workout.setTimeCompleted(currentTime);
		
		LatestCompletionTimeSpecification specification = new LatestCompletionTimeSpecification(currentTime);
		
		boolean isSatisfied = specification.isSatisfiedBy(workout);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_WorkoutWithTimeCompletedAfterLatestCompletionTime_ReturnsFalse() {
		Workout workout = new Workout();
		workout.setTimeCompleted(Dates.rightNow());
		
		LatestCompletionTimeSpecification specification = new LatestCompletionTimeSpecification(Dates.oneHourAgo());
		
		boolean isSatisfied = specification.isSatisfiedBy(workout);
		
		assertFalse(isSatisfied);
	}

}
