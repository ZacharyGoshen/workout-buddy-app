package com.zachgoshen.workouttracker.application.workout.plan;

import java.util.List;

public class WorkoutPlanDto {
	
	private String name;
	private List<WorkoutPlanItemDto> workoutPlanItems;
	
	public WorkoutPlanDto(String name, List<WorkoutPlanItemDto> workoutPlanItems) {
		this.name = name;
		this.workoutPlanItems = workoutPlanItems;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WorkoutPlanItemDto> getWorkoutPlanItems() {
		return workoutPlanItems;
	}

	public void setWorkoutPlanItems(List<WorkoutPlanItemDto> workoutPlanItems) {
		this.workoutPlanItems = workoutPlanItems;
	}

}
