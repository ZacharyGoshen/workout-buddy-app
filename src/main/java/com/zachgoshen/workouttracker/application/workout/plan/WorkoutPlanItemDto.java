package com.zachgoshen.workouttracker.application.workout.plan;

public class WorkoutPlanItemDto {
	
	private int index;
	private String exerciseName;
	private int repRangeMin;
	private int repRangeMax;
	private int secondsToRest;

	public WorkoutPlanItemDto(
		int index, 
		String exerciseName, 
		int repRangeMin, 
		int repRangeMax,
		int secondsToRest
	) {
		this.index = index;
		this.exerciseName = exerciseName;
		this.repRangeMin = repRangeMin;
		this.repRangeMax = repRangeMax;
		this.secondsToRest = secondsToRest;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public int getRepRangeMin() {
		return repRangeMin;
	}

	public void setRepRangeMin(int repRangeMin) {
		this.repRangeMin = repRangeMin;
	}

	public int getRepRangeMax() {
		return repRangeMax;
	}

	public void setRepRangeMax(int repRangeMax) {
		this.repRangeMax = repRangeMax;
	}

	public int getSecondsToRest() {
		return secondsToRest;
	}

	public void setSecondsToRest(int secondsToRest) {
		this.secondsToRest = secondsToRest;
	}

}
