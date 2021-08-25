package com.zachgoshen.workouttracker.data.workout.session;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.data.DataConverter;
import com.zachgoshen.workouttracker.data.workout.plan.WorkoutPlanData;
import com.zachgoshen.workouttracker.domain.workout.WorkoutHeader;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSession;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSessionItem;

public class WorkoutSessionConverter implements DataConverter<WorkoutSession, WorkoutSessionData> {
	
	private static final WorkoutSessionItemConverter workoutSessionItemConverter = new WorkoutSessionItemConverter();

	public WorkoutSessionData convertFromModel(WorkoutSession model) {
		long id = model.getId();
		Date timeCompleted = model.getTimeCompleted();
		List<WorkoutSessionItemData> itemsData = buildSessionItemsData(model);
		
		WorkoutSessionData sessionData = new WorkoutSessionData();
		sessionData.setId(id);
		sessionData.setTimeCompleted(timeCompleted);
		sessionData.setWorkoutSessionItems(itemsData);
		return sessionData;
	}

	public WorkoutSession convertFromData(WorkoutSessionData sessionData) {
		long id = sessionData.getId();
		WorkoutHeader header = buildWorkoutHeader(sessionData);
		Date timeCompleted = sessionData.getTimeCompleted();
		List<WorkoutSessionItem> items = buildSessionItems(sessionData);
		
		return new WorkoutSession(id, header, timeCompleted, items);
	}
	
	private static List<WorkoutSessionItemData> buildSessionItemsData(WorkoutSession sessionModel) {
		return sessionModel.getItems()
			.stream()
			.map(item -> workoutSessionItemConverter.convertFromModel(item))
			.collect(Collectors.toList());
	}
	
	private static WorkoutHeader buildWorkoutHeader(WorkoutSessionData sessionData) {
		WorkoutPlanData planData = sessionData.getWorkoutPlan();
		String name = planData.getName();
		return new WorkoutHeader(name);
	}
	
	private static List<WorkoutSessionItem> buildSessionItems(WorkoutSessionData sessionData) {
		return sessionData.getWorkoutSessionItems()
			.stream()
			.map(itemData -> workoutSessionItemConverter.convertFromData(itemData))
			.collect(Collectors.toList());
	}

}
