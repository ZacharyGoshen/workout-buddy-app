package com.zachgoshen.workouttracker.application.workout.session;

public class WorkoutSessionItemDto {
	
	private String exerciseName;
	private Integer index;
	private Integer repRangeMin;
	private Integer repRangeMax;
	private Integer repsCompleted;
	private Float weightUsed;
	private Integer secondsToRest;
	
	public WorkoutSessionItemDto(
		String exerciseName, 
		Integer index, 
		Integer repRangeMin, 
		Integer repRangeMax,
		Integer repsCompleted, 
		Float weightUsed, 
		Integer secondsToRest
	) {
		this.exerciseName = exerciseName;
		this.index = index;
		this.repRangeMin = repRangeMin;
		this.repRangeMax = repRangeMax;
		this.repsCompleted = repsCompleted;
		this.weightUsed = weightUsed;
		this.secondsToRest = secondsToRest;
	}

	public String getExerciseName() {
		return exerciseName;
	}

	public void setExerciseName(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getRepRangeMin() {
		return repRangeMin;
	}

	public void setRepRangeMin(Integer repRangeMin) {
		this.repRangeMin = repRangeMin;
	}

	public Integer getRepRangeMax() {
		return repRangeMax;
	}

	public void setRepRangeMax(Integer repRangeMax) {
		this.repRangeMax = repRangeMax;
	}

	public Integer getRepsCompleted() {
		return repsCompleted;
	}

	public void setRepsCompleted(Integer repsCompleted) {
		this.repsCompleted = repsCompleted;
	}

	public Float getWeightUsed() {
		return weightUsed;
	}

	public void setWeightUsed(Float weightUsed) {
		this.weightUsed = weightUsed;
	}

	public Integer getSecondsToRest() {
		return secondsToRest;
	}

	public void setSecondsToRest(Integer secondsToRest) {
		this.secondsToRest = secondsToRest;
	}

}
