package com.zachgoshen.workouttracker.domain.workout.plan;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;

public class WorkoutPlanItem {
	
	private long id;
	private int index;
	private int repRangeLowerBound;
	private int repRangeUpperBound;
	private int secondsToRest;
	private Exercise exercise;
	
	public WorkoutPlanItem(long id, int index, int repRangeLowerBound, int repRangeUpperBound, int secondsToRest, Exercise exercise) {
		this.id = id;
		this.index = index;
		this.repRangeLowerBound = repRangeLowerBound;
		this.repRangeUpperBound = repRangeUpperBound;
		this.secondsToRest = secondsToRest;
		this.exercise = exercise;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getRepRangeLowerBound() {
		return repRangeLowerBound;
	}

	public void setRepRangeLowerBound(int repRangeLowerBound) {
		this.repRangeLowerBound = repRangeLowerBound;
	}

	public int getRepRangeUpperBound() {
		return repRangeUpperBound;
	}

	public void setRepRangeUpperBound(int repRangeUpperBound) {
		this.repRangeUpperBound = repRangeUpperBound;
	}

	public int getSecondsToRest() {
		return secondsToRest;
	}

	public void setSecondsToRest(int secondsToRest) {
		this.secondsToRest = secondsToRest;
	}

	public Exercise getExercise() {
		return exercise;
	}

	public void setExercise(Exercise exercise) {
		this.exercise = exercise;
	}

}
