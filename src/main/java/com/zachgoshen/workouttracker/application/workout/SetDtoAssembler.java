package com.zachgoshen.workouttracker.application.workout;

import java.util.Arrays;
import java.util.Date;
import java.util.Optional;

import com.zachgoshen.workouttracker.domain.workout.set.Set;
import com.zachgoshen.workouttracker.domain.workout.set.SingleExerciseSet;

public class SetDtoAssembler {
	
	public static SetDto assemble(Set set) {
		if (set instanceof SingleExerciseSet) {
			return assemble((SingleExerciseSet) set);
		} else {
			return new SetDto();
		}
	}
	
	private static SetDto assemble(SingleExerciseSet set) {
		SetDto dto = new SetDto();
		
		Optional<Date> timeCompleted = set.getTimeCompleted();
		if (timeCompleted.isPresent()) {
			dto.setTimeCompleted(timeCompleted.get());
		}
		
		Optional<Float> timeRested = set.getTimeRested();
		if (timeRested.isPresent()) {
			dto.setTimeRested(timeRested.get());
		}
		
		Optional<Float> minimumRestTimeAllowed = set.getMinimumRestTimeAllowed();
		if (minimumRestTimeAllowed.isPresent()) {
			dto.setMinimumRestTimeAllowed(minimumRestTimeAllowed.get());
		}
		
		Optional<Float> maximumRestTimeAllowed = set.getMaximumRestTimeAllowed();
		if (maximumRestTimeAllowed.isPresent()) {
			dto.setMaximumRestTimeAllowed(maximumRestTimeAllowed.get());
		}
		
		ExerciseDto exercise = ExerciseDtoAssembler.assemble(set.getExercise());
		dto.setExercises(Arrays.asList(exercise));
		
		return dto;
	}

}
