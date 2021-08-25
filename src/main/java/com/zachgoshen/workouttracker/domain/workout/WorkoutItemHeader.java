package com.zachgoshen.workouttracker.domain.workout;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;

public class WorkoutItemHeader {

	private int index;
	private int repRangeLowerBound;
	private int repRangeUpperBound;
	private int secondsToRest;
	private Exercise exercise;
	
	public WorkoutItemHeader(
			int index, 
			int repRangeLowerBound, 
			int repRangeUpperBound, 
			int secondsToRest,
			Exercise exercise) {
		this.index = index;
		this.repRangeLowerBound = repRangeLowerBound;
		this.repRangeUpperBound = repRangeUpperBound;
		this.secondsToRest = secondsToRest;
		this.exercise = exercise;
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
