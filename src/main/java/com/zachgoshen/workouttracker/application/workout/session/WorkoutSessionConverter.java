package com.zachgoshen.workouttracker.application.workout.session;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSession;

public class WorkoutSessionConverter implements ModelToDtoConverter<WorkoutSession, WorkoutSessionDto> {
	
	private final WorkoutSessionItemConverter itemConverter = new WorkoutSessionItemConverter();

	public WorkoutSessionDto convertFromModel(WorkoutSession model) {
		List<WorkoutSessionItemDto> itemDtos = (
			model.getItems()
				.stream()
				.map(item -> itemConverter.convertFromModel(item))
				.collect(Collectors.toList()));
		
		return new WorkoutSessionDto(model.getName(), model.getTimeCompleted(), itemDtos);
	}

}
