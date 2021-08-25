package com.zachgoshen.workouttracker.application.workout.plan;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanItem;

public class WorkoutPlanItemConverter implements ModelToDtoConverter<WorkoutPlanItem, WorkoutPlanItemDto> {

	public WorkoutPlanItemDto convertFromModel(WorkoutPlanItem model) {
		return new WorkoutPlanItemDto(
			model.getHeader().getIndex(), 
			model.getHeader().getExercise().getName(), 
			model.getHeader().getRepRangeLowerBound(), 
			model.getHeader().getRepRangeUpperBound(), 
			model.getHeader().getSecondsToRest());
	}

}
