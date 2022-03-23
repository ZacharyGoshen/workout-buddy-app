package com.zachgoshen.workoutbuddy.api.set;

import java.util.Date;
import java.util.List;

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
		
		Integer setNumber = getSetNumber(set, workout);
		setWithWorkoutDetailsDto.setSetNumber(setNumber);
		
		return setWithWorkoutDetailsDto;
	}
	
	private static Integer getSetNumber(Set set, Workout workout) {
		String setId = set.getId();
		List<Set> workoutSets = workout.getSets();
		
		for (int i = 0; i < workoutSets.size(); i++) {
			Set workoutSet = workoutSets.get(i);
			String workoutSetId = workoutSet.getId();
			
			if (setId.equals(workoutSetId)) {
				return i;
			}
		}
		
		return null;
	}
	
}
