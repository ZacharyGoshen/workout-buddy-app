package com.zachgoshen.workouttracker.application.workout.session;

import java.util.Date;
import java.util.List;

public class WorkoutSessionDto {
	
	private String workoutPlanName;
	private Date timeCompleted;
	private List<WorkoutSessionItemDto> workoutSessionItems;
	
	public WorkoutSessionDto(
			String workoutPlanName, 
			Date timeCompleted,
			List<WorkoutSessionItemDto> workoutSessionItems) {
		this.workoutPlanName = workoutPlanName;
		this.timeCompleted = timeCompleted;
		this.workoutSessionItems = workoutSessionItems;
	}

	public String getWorkoutPlanName() {
		return workoutPlanName;
	}
	
	public void setWorkoutPlanName(String workoutPlanName) {
		this.workoutPlanName = workoutPlanName;
	}
	
	public Date getTimeCompleted() {
		return timeCompleted;
	}
	
	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = timeCompleted;
	}
	
	public List<WorkoutSessionItemDto> getWorkoutSessionItems() {
		return workoutSessionItems;
	}
	
	public void setWorkoutSessionItems(List<WorkoutSessionItemDto> workoutSessionItems) {
		this.workoutSessionItems = workoutSessionItems;
	}

}
