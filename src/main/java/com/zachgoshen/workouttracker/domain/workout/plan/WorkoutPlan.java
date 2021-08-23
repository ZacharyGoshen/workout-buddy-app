package com.zachgoshen.workouttracker.domain.workout.plan;

import java.util.List;

public class WorkoutPlan {
	
	private Long id;
	private String name;
	private List<WorkoutPlanItem> workoutPlanItems;
	
	public WorkoutPlan(Long id, String name, List<WorkoutPlanItem> workoutPlanItems) {
		this.id = id;
		this.name = name;
		this.workoutPlanItems = workoutPlanItems;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<WorkoutPlanItem> getWorkoutPlanItems() {
		return workoutPlanItems;
	}
	
	public void setWorkoutPlanItems(List<WorkoutPlanItem> workoutPlanItems) {
		this.workoutPlanItems = workoutPlanItems;
	}

}
