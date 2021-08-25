package com.zachgoshen.workouttracker.application.workout.plan;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.WorkoutHeader;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlan;

public class WorkoutPlanConverter implements ModelToDtoConverter<WorkoutPlan, WorkoutPlanDto> {
	
	private final WorkoutPlanItemConverter itemConverter = new WorkoutPlanItemConverter();

	public WorkoutPlanDto convertFromModel(WorkoutPlan model) {
		WorkoutHeader header = model.getHeader();
		String name = header.getName();
		
		List<WorkoutPlanItemDto> itemsDto = buildItemDtos(model);
		
		return new WorkoutPlanDto(name, itemsDto);
	}
	
	private List<WorkoutPlanItemDto> buildItemDtos(WorkoutPlan model) {
		return model.getWorkoutPlanItems()
			.stream()
			.map(item -> itemConverter.convertFromModel(item))
			.collect(Collectors.toList());
	}

}
