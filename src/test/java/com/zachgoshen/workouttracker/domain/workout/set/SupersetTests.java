package com.zachgoshen.workouttracker.domain.workout.set;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;

public class SupersetTests {
	
	@Test
	public void Clone_TimeRestedIsSet_CopyHasSameTimeRestedValue() {
		List<Exercise> exercises = buildExercises();
		Superset set = new Superset(exercises);
		set.setTimeRested(75);
		
		Superset copy = set.clone();
		
		assertEquals(75, copy.getTimeRested().get());
	}
	
	@Test
	public void Clone_MinimumRestTimeAllowedIsSet_CopyHasSameMinimumRestTimeAllowedValue() throws InvalidRangeException {
		List<Exercise> exercises = buildExercises();
		Superset set = new Superset(exercises);
		set.addBoundedRestTimeConstraint(30, 60);
		
		Superset copy = set.clone();
		
		assertEquals(30, copy.getMinimumRestTimeAllowed().get());
	}
	
	@Test
	public void Clone_MaximumRestTimeAllowedIsSet_CopyHasSameMaximumRestTimeAllowedValue() throws InvalidRangeException {
		List<Exercise> exercises = buildExercises();
		Superset set = new Superset(exercises);
		set.addBoundedRestTimeConstraint(30, 60);
		
		Superset copy = set.clone();
		
		assertEquals(60, copy.getMaximumRestTimeAllowed().get());
	}
	
	private static List<Exercise> buildExercises() {
		Exercise exercise1 = buildExercise();
		Exercise exercise2 = buildExercise();
		Exercise exercise3 = buildExercise();
		
		return Arrays.asList(exercise1, exercise2, exercise3);
	}
	
	private static Exercise buildExercise() {
		ExerciseDescription description = new ExerciseDescription("description");
		return new Exercise(description);
	}

}
