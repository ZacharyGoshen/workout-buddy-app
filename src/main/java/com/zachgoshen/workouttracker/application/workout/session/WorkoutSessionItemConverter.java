package com.zachgoshen.workouttracker.application.workout.session;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSessionItem;

public class WorkoutSessionItemConverter implements ModelToDtoConverter<WorkoutSessionItem, WorkoutSessionItemDto> {

	public WorkoutSessionItemDto convertFromModel(WorkoutSessionItem model) {
		return new WorkoutSessionItemDto(
			model.getPlanItem().getExercise().getName(),
			model.getPlanItem().getIndex(),
			model.getPlanItem().getRepRangeLowerBound(),
			model.getPlanItem().getRepRangeUpperBound(),
			model.getRepsCompleted(),
			model.getWeightUsed(),
			model.getPlanItem().getSecondsToRest());
	}

}
