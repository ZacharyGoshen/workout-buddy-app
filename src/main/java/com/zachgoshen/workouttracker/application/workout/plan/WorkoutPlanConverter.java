package com.zachgoshen.workouttracker.application.workout.plan;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlan;

public class WorkoutPlanConverter implements ModelToDtoConverter<WorkoutPlan, WorkoutPlanDto> {
	
	private final WorkoutPlanItemConverter itemConverter = new WorkoutPlanItemConverter();

	public WorkoutPlanDto convertFromModel(WorkoutPlan model) {
		List<WorkoutPlanItemDto> itemsDto = (
			model.getWorkoutPlanItems()
				.stream()
				.map(item -> itemConverter.convertFromModel(item))
				.collect(Collectors.toList())
		);
		
		return new WorkoutPlanDto(model.getName(), itemsDto);
	}

}
