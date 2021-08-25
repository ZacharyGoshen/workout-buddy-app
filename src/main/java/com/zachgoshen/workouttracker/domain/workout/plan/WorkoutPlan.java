package com.zachgoshen.workouttracker.domain.workout.plan;

import java.util.List;

import com.zachgoshen.workouttracker.domain.workout.WorkoutHeader;

public class WorkoutPlan {
	
	private Long id;
	private WorkoutHeader header;
	private List<WorkoutPlanItem> workoutPlanItems;
	
	public WorkoutPlan(Long id, WorkoutHeader header, List<WorkoutPlanItem> workoutPlanItems) {
		this.id = id;
		this.header = header;
		this.workoutPlanItems = workoutPlanItems;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public WorkoutHeader getHeader() {
		return header;
	}

	public void setHeader(WorkoutHeader header) {
		this.header = header;
	}

	public List<WorkoutPlanItem> getWorkoutPlanItems() {
		return workoutPlanItems;
	}
	
	public void setWorkoutPlanItems(List<WorkoutPlanItem> workoutPlanItems) {
		this.workoutPlanItems = workoutPlanItems;
	}

}
