package com.zachgoshen.workouttracker.application.workout.session;

import com.zachgoshen.workouttracker.application.ModelToDtoConverter;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSessionItem;

public class WorkoutSessionItemConverter implements ModelToDtoConverter<WorkoutSessionItem, WorkoutSessionItemDto> {

	public WorkoutSessionItemDto convertFromModel(WorkoutSessionItem model) {
		return new WorkoutSessionItemDto(
			model.getPlanItem().getHeader().getExercise().getName(),
			model.getPlanItem().getHeader().getIndex(),
			model.getPlanItem().getHeader().getRepRangeLowerBound(),
			model.getPlanItem().getHeader().getRepRangeUpperBound(),
			model.getRepsCompleted(),
			model.getWeightUsed(),
			model.getPlanItem().getHeader().getSecondsToRest());
	}

}
