package com.zachgoshen.workouttracker.domain.application.workout;

import static com.zachgoshen.workouttracker.domain.application.workout.set.SetDtoAssertions.assertSetDtoMatchesSet;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zachgoshen.workouttracker.application.workout.WorkoutDto;
import com.zachgoshen.workouttracker.domain.workout.Workout;

public class WorkoutDtoAssertions {
	
	public static void assertWorkoutDtoMatchesWorkout(WorkoutDto dto, Workout workout) {
		assertEquals(workout.getName().orElse(null), dto.getName());
		assertEquals(workout.getTimeCompleted().orElse(null), dto.getTimeCompleted());
		
		assertEquals(workout.getSets().size(), dto.getSets().size());
		
		for (int i = 0; i < workout.getSets().size(); i++) {
			assertSetDtoMatchesSet(dto.getSets().get(i), workout.getSets().get(i));
		}
	}

}
