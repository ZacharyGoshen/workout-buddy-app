package com.zachgoshen.workouttracker.application.workout.session;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.WorkoutHeader;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSession;

public class WorkoutSessionConverter implements ModelToDtoConverter<WorkoutSession, WorkoutSessionDto> {
	
	private static final WorkoutSessionItemConverter itemConverter = new WorkoutSessionItemConverter();

	public WorkoutSessionDto convertFromModel(WorkoutSession model) {
		WorkoutHeader header = model.getHeader();
		String name = header.getName();
		
		Date timeCompleted = model.getTimeCompleted();
		List<WorkoutSessionItemDto> itemDtos = buildSessionItemDtos(model);
		
		return new WorkoutSessionDto(name, timeCompleted, itemDtos);
	}
	
	public static List<WorkoutSessionItemDto> buildSessionItemDtos(WorkoutSession model) {
		return model.getItems()
			.stream()
			.map(item -> itemConverter.convertFromModel(item))
			.collect(Collectors.toList());
	}

}
