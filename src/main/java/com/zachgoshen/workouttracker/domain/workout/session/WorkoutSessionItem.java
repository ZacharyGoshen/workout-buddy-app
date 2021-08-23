package com.zachgoshen.workouttracker.domain.workout.session;

import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanItem;

public class WorkoutSessionItem {
	
	private long id;
	private int repsCompleted;
	private float weightUsed;
	private WorkoutPlanItem planItem;
	
	public WorkoutSessionItem(long id, int repsCompleted, float weightUsed, WorkoutPlanItem planItem) {
		this.id = id;
		this.repsCompleted = repsCompleted;
		this.weightUsed = weightUsed;
		this.planItem = planItem;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getRepsCompleted() {
		return repsCompleted;
	}

	public void setRepsCompleted(int repsCompleted) {
		this.repsCompleted = repsCompleted;
	}

	public float getWeightUsed() {
		return weightUsed;
	}

	public void setWeightUsed(float weightUsed) {
		this.weightUsed = weightUsed;
	}

	public WorkoutPlanItem getPlanItem() {
		return planItem;
	}

	public void setPlanItem(WorkoutPlanItem planItem) {
		this.planItem = planItem;
	}

}
