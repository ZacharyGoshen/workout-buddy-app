package com.zachgoshen.workouttracker.data.workout.session;

import com.zachgoshen.workouttracker.data.DataConverter;
import com.zachgoshen.workouttracker.data.workout.plan.WorkoutPlanItemConverter;
import com.zachgoshen.workouttracker.data.workout.plan.WorkoutPlanItemData;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanItem;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSessionItem;

public class WorkoutSessionItemConverter implements DataConverter<WorkoutSessionItem, WorkoutSessionItemData> {
	
	private final WorkoutPlanItemConverter planItemConverter = new WorkoutPlanItemConverter();

	public WorkoutSessionItemData convertFromModel(WorkoutSessionItem model) {
		WorkoutPlanItemData planItemData = planItemConverter.convertFromModel(model.getPlanItem());
		
		WorkoutSessionItemData sessionItemData = new WorkoutSessionItemData();
		sessionItemData.setId(model.getId());
		sessionItemData.setRepsCompleted(model.getRepsCompleted());
		sessionItemData.setWeightUsed(model.getWeightUsed());
		sessionItemData.setWorkoutPlanItem(planItemData);
		return sessionItemData;
	}

	public WorkoutSessionItem convertFromData(WorkoutSessionItemData data) {
		WorkoutPlanItem planItem = planItemConverter.convertFromData(data.getWorkoutPlanItem());
		return new WorkoutSessionItem(data.getId(), data.getRepsCompleted(), data.getWeightUsed(), planItem);
	}

}
