package com.zachgoshen.workoutbuddy.domain.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExerciseAssertions {
	
	public static void assertExercisesAreEqual(Exercise exercise1, Exercise exercise2) {
		assertEquals(exercise1.getDescription().getName(), exercise2.getDescription().getName());
		assertEquals(exercise1.getWeightUsed().get(), exercise2.getWeightUsed().get());
		assertEquals(exercise1.getMinimumWeightAllowed().get(), exercise2.getMinimumWeightAllowed().get());
		assertEquals(exercise1.getMaximumWeightAllowed().get(), exercise2.getMaximumWeightAllowed().get());
		assertEquals(exercise1.getRepsCompleted().get(), exercise2.getRepsCompleted().get());
		assertEquals(exercise1.getMinimumRepsAllowed().get(), exercise2.getMinimumRepsAllowed().get());
		assertEquals(exercise1.getMaximumRepsAllowed().get(), exercise2.getMaximumRepsAllowed().get());
		assertEquals(exercise1.getTimePerformed().get(), exercise2.getTimePerformed().get());
		assertEquals(exercise1.getMinimumDurationAllowed().get(), exercise2.getMinimumDurationAllowed().get());
		assertEquals(exercise1.getMaximumDurationAllowed().get(), exercise2.getMaximumDurationAllowed().get());
	}

}
