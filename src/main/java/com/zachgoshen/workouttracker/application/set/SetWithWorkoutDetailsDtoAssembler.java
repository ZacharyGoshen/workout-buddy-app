package com.zachgoshen.workouttracker.application.set;

import java.util.Date;

import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.workout.Workout;

public class SetWithWorkoutDetailsDtoAssembler {

	public static SetWithWorkoutDetailsDto assemble(Set set, Workout workout) {
		SetDto setDto = SetDtoAssembler.assemble(set);
		SetWithWorkoutDetailsDto setWithWorkoutDetailsDto = new SetWithWorkoutDetailsDto(setDto);
		
		String workoutName = workout.getName().orElse(null);
		setWithWorkoutDetailsDto.setWorkoutName(workoutName);
		
		Date workoutCompletionTime = workout.getTimeCompleted().orElse(null);
		setWithWorkoutDetailsDto.setWorkoutCompletionTime(workoutCompletionTime);
		
		return setWithWorkoutDetailsDto;
	}
	
}
