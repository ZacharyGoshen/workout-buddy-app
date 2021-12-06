package com.zachgoshen.workouttracker.domain.workout.set.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.common.date.Dates;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.workout.set.Set;
import com.zachgoshen.workouttracker.domain.workout.set.SingleExerciseSet;

public class LatestCompletionTimeSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_SetWithTimeCompletedBeforeLatestCompletionTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(Dates.rightNow());
		
		LatestCompletionTimeSpecification specification = new LatestCompletionTimeSpecification(Dates.oneHourFromNow());
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeCompletedEqualToLatestCompletionTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Date currentTime = Dates.rightNow();
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(currentTime);
		
		LatestCompletionTimeSpecification specification = new LatestCompletionTimeSpecification(currentTime);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeCompletedAfterLatestsCompletionTime_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(Dates.rightNow());
		
		LatestCompletionTimeSpecification specification = new LatestCompletionTimeSpecification(Dates.oneHourAgo());
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}

}
