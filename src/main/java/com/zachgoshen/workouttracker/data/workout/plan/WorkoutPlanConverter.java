package com.zachgoshen.workouttracker.data.workout.plan;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.data.DataConverter;
import com.zachgoshen.workouttracker.domain.workout.WorkoutHeader;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlan;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanItem;

public class WorkoutPlanConverter implements DataConverter<WorkoutPlan, WorkoutPlanData> {
	
	private final WorkoutPlanItemConverter itemConverter = new WorkoutPlanItemConverter();

	public WorkoutPlanData convertFromModel(WorkoutPlan model) {
		List<WorkoutPlanItemData> itemsData = buildItemsData(model);
		
		WorkoutPlanData planData = new WorkoutPlanData();
		planData.setId(model.getId());
		planData.setName(model.getHeader().getName());
		planData.setWorkoutPlanItems(itemsData);
		return planData;
	}

	public WorkoutPlan convertFromData(WorkoutPlanData data) {
		WorkoutHeader header = new WorkoutHeader(data.getName());
		List<WorkoutPlanItem> items = buildItems(data);
		return new WorkoutPlan(data.getId(), header, items);
	}
	
	private List<WorkoutPlanItemData> buildItemsData(WorkoutPlan model) {
		return model.getWorkoutPlanItems()
			.stream()
			.map(item -> itemConverter.convertFromModel(item))
			.collect(Collectors.toList());
	}
	
	private List<WorkoutPlanItem> buildItems(WorkoutPlanData data) {
		return data.getWorkoutPlanItems()
			.stream()
			.map(itemData -> itemConverter.convertFromData(itemData))
			.collect(Collectors.toList());
	}

}
