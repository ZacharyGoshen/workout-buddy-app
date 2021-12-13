package com.zachgoshen.workouttracker.application.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.application.exercise.ExerciseDto;
import com.zachgoshen.workouttracker.application.exercise.ExerciseDtoAssembler;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;

public class SetDtoAssembler {
	
	public static SetDto assemble(Set set) {
		SetDto setDto = assembleWithoutExercises(set);
		
		List<ExerciseDto> exerciseDtos = new ArrayList<>();
		
		if (set instanceof SingleExerciseSet) {
			SingleExerciseSet singleExerciseSet = (SingleExerciseSet) set;
			
			setDto.setType("Single Exercise Set");
			
			ExerciseDto exerciseDto = ExerciseDtoAssembler.assemble(singleExerciseSet.getExercise());
			exerciseDtos = Arrays.asList(exerciseDto);
		} else if (set instanceof Superset) {
			Superset superset = (Superset) set;
			
			setDto.setType("Superset");
			
			exerciseDtos = superset.getExercises()
				.stream()
				.map(exercise -> ExerciseDtoAssembler.assemble(exercise))
				.collect(Collectors.toList());
		}
		
		setDto.setExercises(exerciseDtos);
		
		return setDto;
	}
	
	private static SetDto assembleWithoutExercises(Set set) {
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
		
		return dto;
	}

}
