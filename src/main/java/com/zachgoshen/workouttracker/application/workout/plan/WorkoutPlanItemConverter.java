package com.zachgoshen.workouttracker.application.workout.plan;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanItem;

public class WorkoutPlanItemConverter implements ModelToDtoConverter<WorkoutPlanItem, WorkoutPlanItemDto> {

	public WorkoutPlanItemDto convertFromModel(WorkoutPlanItem model) {
		return new WorkoutPlanItemDto(
			model.getIndex(), 
			model.getExercise().getName(), 
			model.getRepRangeLowerBound(), 
			model.getRepRangeUpperBound(), 
			model.getSecondsToRest()
		);
	}

}
