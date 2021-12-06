package com.zachgoshen.workouttracker.application.workout.set;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zachgoshen.workouttracker.application.workout.exercise.ExerciseDto;

public class SetDto {
	
	@JsonProperty("timeCompleted")
	private Date timeCompleted;
	
	@JsonProperty("timeRested")
	private Float timeRested;
	
	@JsonProperty("minimumRestTimeAllowed")
	private Float minimumRestTimeAllowed;
	
	@JsonProperty("maximumRestTimeAllowed")
	private Float maximumRestTimeAllowed;
	
	@JsonProperty("exercises")
	private List<ExerciseDto> exercises;

	public Date getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = timeCompleted;
	}

	public Float getTimeRested() {
		return timeRested;
	}

	public void setTimeRested(Float timeRested) {
		this.timeRested = timeRested;
	}

	public Float getMinimumRestTimeAllowed() {
		return minimumRestTimeAllowed;
	}

	public void setMinimumRestTimeAllowed(Float minimumRestTimeAllowed) {
		this.minimumRestTimeAllowed = minimumRestTimeAllowed;
	}

	public Float getMaximumRestTimeAllowed() {
		return maximumRestTimeAllowed;
	}

	public void setMaximumRestTimeAllowed(Float maximumRestTimeAllowed) {
		this.maximumRestTimeAllowed = maximumRestTimeAllowed;
	}

	public List<ExerciseDto> getExercises() {
		return exercises;
	}

	public void setExercises(List<ExerciseDto> exercises) {
		this.exercises = exercises;
	}

}
