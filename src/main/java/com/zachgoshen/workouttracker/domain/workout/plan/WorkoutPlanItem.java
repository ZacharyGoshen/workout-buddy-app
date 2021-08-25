package com.zachgoshen.workouttracker.domain.workout.plan;

import com.zachgoshen.workouttracker.domain.workout.WorkoutItemHeader;

public class WorkoutPlanItem {
	
	private long id;
	private WorkoutItemHeader header;
	
	public WorkoutPlanItem(long id, WorkoutItemHeader header) {
		this.id = id;
		this.header = header;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public WorkoutItemHeader getHeader() {
		return header;
	}

	public void setHeader(WorkoutItemHeader header) {
		this.header = header;
	}

}
