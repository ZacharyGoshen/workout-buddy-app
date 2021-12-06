package com.zachgoshen.workouttracker.domain.application.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zachgoshen.workouttracker.application.exercise.ExerciseDto;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;

public class ExerciseDtoAssertions {
	
	public static void assertExerciseDtoMatchesExercise(ExerciseDto dto, Exercise exercise) {
		assertEquals(exercise.getDescription().getName(), dto.getName());
		assertEquals(exercise.getWeightUsed().orElse(null), dto.getWeightUsed());
		assertEquals(exercise.getMinimumWeightAllowed().orElse(null), dto.getMinimumWeightAllowed());
		assertEquals(exercise.getMaximumWeightAllowed().orElse(null), dto.getMaximumWeightAllowed());
		assertEquals(exercise.getRepsCompleted().orElse(null), dto.getRepsCompleted());
		assertEquals(exercise.getMinimumRepsAllowed().orElse(null), dto.getMinimumRepsAllowed());
		assertEquals(exercise.getMaximumRepsAllowed().orElse(null), dto.getMaximumRepsAllowed());
		assertEquals(exercise.getTimePerformed().orElse(null), dto.getTimePerformed());
		assertEquals(exercise.getMinimumDurationAllowed().orElse(null), dto.getMinimumDurationAllowed());
		assertEquals(exercise.getMaximumDurationAllowed().orElse(null), dto.getMaximumDurationAllowed());
	}

}
