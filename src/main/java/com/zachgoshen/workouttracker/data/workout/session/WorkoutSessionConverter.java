package com.zachgoshen.workouttracker.data.workout.session;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.data.DataConverter;
import com.zachgoshen.workouttracker.domain.workout.WorkoutHeader;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSession;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSessionItem;

public class WorkoutSessionConverter implements DataConverter<WorkoutSession, WorkoutSessionData> {
	
	private static final WorkoutSessionItemConverter workoutSessionItemConverter = new WorkoutSessionItemConverter();

	public WorkoutSessionData convertFromModel(WorkoutSession model) {
		List<WorkoutSessionItemData> itemsData = buildSessionItemsData(model);
		
		WorkoutSessionData sessionData = new WorkoutSessionData();
		sessionData.setId(model.getId());
		sessionData.setTimeCompleted(model.getTimeCompleted());
		sessionData.setWorkoutSessionItems(itemsData);
		return sessionData;
	}

	public WorkoutSession convertFromData(WorkoutSessionData data) {
		List<WorkoutSessionItem> items = buildSessionItems(data);
		
		return new WorkoutSession(
			data.getId(), 
			buildWorkoutHeader(data), 
			data.getTimeCompleted(), 
			items);
	}
	
	private static List<WorkoutSessionItemData> buildSessionItemsData(WorkoutSession model) {
		return model.getItems()
			.stream()
			.map(item -> workoutSessionItemConverter.convertFromModel(item))
			.collect(Collectors.toList());
	}
	
	private static WorkoutHeader buildWorkoutHeader(WorkoutSessionData data) {
		return new WorkoutHeader(data.getWorkoutPlan().getName());
	}
	
	private static List<WorkoutSessionItem> buildSessionItems(WorkoutSessionData data) {
		return data.getWorkoutSessionItems()
			.stream()
			.map(itemData -> workoutSessionItemConverter.convertFromData(itemData))
			.collect(Collectors.toList());
	}

}
