package com.zachgoshen.workouttracker.data.workout.session;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.data.DataConverter;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSession;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSessionItem;

public class WorkoutSessionConverter implements DataConverter<WorkoutSession, WorkoutSessionData> {
	
	private final WorkoutSessionItemConverter workoutSessionItemConverter = new WorkoutSessionItemConverter();

	public WorkoutSessionData convertFromModel(WorkoutSession model) {
		List<WorkoutSessionItemData> itemsData = (
			model.getItems()
				.stream()
				.map(item -> workoutSessionItemConverter.convertFromModel(item))
				.collect(Collectors.toList()));
		
		WorkoutSessionData sessionData = new WorkoutSessionData();
		sessionData.setId(model.getId());
		sessionData.setTimeCompleted(model.getTimeCompleted());
		sessionData.setWorkoutSessionItems(itemsData);
		
		return sessionData;
	}

	public WorkoutSession convertFromData(WorkoutSessionData data) {
		List<WorkoutSessionItem> items = (
			data.getWorkoutSessionItems()
				.stream()
				.map(itemData -> workoutSessionItemConverter.convertFromData(itemData))
				.collect(Collectors.toList()));
		
		return new WorkoutSession(data.getId(), data.getWorkoutPlan().getName(), data.getTimeCompleted(), items);
	}

}
