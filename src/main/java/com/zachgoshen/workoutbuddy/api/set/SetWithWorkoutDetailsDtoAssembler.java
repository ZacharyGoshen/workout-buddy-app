package com.zachgoshen.workoutbuddy.api.set;

import java.util.Date;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public final class SetWithWorkoutDetailsDtoAssembler {
	
	private SetWithWorkoutDetailsDtoAssembler() {}

	public static SetWithWorkoutDetailsDto assemble(Set set, Workout workout) throws DtoConversionException {
		SetDto setDto = SetConverter.toDto(set);
		SetWithWorkoutDetailsDto setWithWorkoutDetailsDto = new SetWithWorkoutDetailsDto(setDto);
		
		String workoutId = workout.getId();
		setWithWorkoutDetailsDto.setWorkoutId(workoutId);
		
		String workoutName = workout.getName().orElse(null);
		setWithWorkoutDetailsDto.setWorkoutName(workoutName);
		
		Date workoutCompletionTime = workout.getTimeCompleted().orElse(null);
		setWithWorkoutDetailsDto.setWorkoutCompletionTime(workoutCompletionTime);
		
		return setWithWorkoutDetailsDto;
	}
	
}
