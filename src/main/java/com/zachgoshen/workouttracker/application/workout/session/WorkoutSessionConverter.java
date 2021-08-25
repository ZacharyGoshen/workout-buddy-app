package com.zachgoshen.workouttracker.application.workout.session;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSession;

public class WorkoutSessionConverter implements ModelToDtoConverter<WorkoutSession, WorkoutSessionDto> {
	
	private static final WorkoutSessionItemConverter itemConverter = new WorkoutSessionItemConverter();

	public WorkoutSessionDto convertFromModel(WorkoutSession model) {
		List<WorkoutSessionItemDto> itemDtos = buildSessionItemDtos(model);
		
		return new WorkoutSessionDto(
			model.getHeader().getName(), 
			model.getTimeCompleted(), 
			itemDtos);
	}
	
	public static List<WorkoutSessionItemDto> buildSessionItemDtos(WorkoutSession model) {
		return model.getItems()
			.stream()
			.map(item -> itemConverter.convertFromModel(item))
			.collect(Collectors.toList());
	}

}
