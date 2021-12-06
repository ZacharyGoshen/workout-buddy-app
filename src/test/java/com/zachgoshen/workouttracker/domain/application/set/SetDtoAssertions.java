package com.zachgoshen.workouttracker.domain.application.set;

import static com.zachgoshen.workouttracker.domain.application.exercise.ExerciseDtoAssertions.assertExerciseDtoMatchesExercise;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.zachgoshen.workouttracker.application.set.SetDto;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;

public class SetDtoAssertions {
	
	public static void assertSetDtoMatchesSet(SetDto dto, Set set) {
		assertEquals(set.getTimeCompleted().orElse(null), dto.getTimeCompleted());
		assertEquals(set.getTimeRested().orElse(null), dto.getTimeRested());
		assertEquals(set.getMinimumRestTimeAllowed().orElse(null), dto.getMinimumRestTimeAllowed());
		assertEquals(set.getMaximumRestTimeAllowed().orElse(null), dto.getMaximumRestTimeAllowed());
		
		if (set instanceof SingleExerciseSet) {
			assertSetDtoOnlyContainsExerciseFromSingleExerciseSet(dto, (SingleExerciseSet) set);
		} else if (set instanceof Superset) {
			assertSetDtoOnlyContainsExercisesFromSuperset(dto, (Superset) set);
		}
	}
	
	private static void assertSetDtoOnlyContainsExerciseFromSingleExerciseSet(SetDto dto, SingleExerciseSet set) {
		assertEquals(1, dto.getExercises().size());
		assertExerciseDtoMatchesExercise(dto.getExercises().get(0), set.getExercise());
	}
	
	private static void assertSetDtoOnlyContainsExercisesFromSuperset(SetDto dto, Superset set) {
		assertEquals(set.getExercises().size(), dto.getExercises().size());
		
		for (int i = 0; i < set.getExercises().size(); i++) {
			assertExerciseDtoMatchesExercise(dto.getExercises().get(i), set.getExercises().get(i));
		}
	}

}
