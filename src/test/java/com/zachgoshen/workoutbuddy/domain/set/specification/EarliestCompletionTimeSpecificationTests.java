package com.zachgoshen.workoutbuddy.domain.set.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.common.date.Dates;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.specification.EarliestCompletionTimeSpecification;

public class EarliestCompletionTimeSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_SetWithTimeCompletedAfterEarliestCompletionTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(Dates.rightNow());
		
		EarliestCompletionTimeSpecification specification = new EarliestCompletionTimeSpecification(Dates.oneHourAgo());
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeCompletedEqualToEarliestCompletionTime_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Date currentTime = Dates.rightNow();
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(currentTime);
		
		EarliestCompletionTimeSpecification specification = new EarliestCompletionTimeSpecification(currentTime);
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SetWithTimeCompletedBeforeEarliestCompletionTime_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		Set set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(Dates.rightNow());
		
		EarliestCompletionTimeSpecification specification = new EarliestCompletionTimeSpecification(Dates.oneHourFromNow());
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}

}
