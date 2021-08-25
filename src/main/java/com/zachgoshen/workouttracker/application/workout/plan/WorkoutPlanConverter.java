package com.zachgoshen.workouttracker.application.workout.plan;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlan;

public class WorkoutPlanConverter implements ModelToDtoConverter<WorkoutPlan, WorkoutPlanDto> {
	
	private static final WorkoutPlanItemConverter itemConverter = new WorkoutPlanItemConverter();

	public WorkoutPlanDto convertFromModel(WorkoutPlan model) {
		List<WorkoutPlanItemDto> itemsDto = buildItemDtos(model);
		return new WorkoutPlanDto(model.getHeader().getName(), itemsDto);
	}
	
	private static List<WorkoutPlanItemDto> buildItemDtos(WorkoutPlan model) {
		return model.getWorkoutPlanItems()
			.stream()
			.map(item -> itemConverter.convertFromModel(item))
			.collect(Collectors.toList());
	}

}
