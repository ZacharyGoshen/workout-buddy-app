package com.zachgoshen.workoutbuddy.api.set;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDto;

public class SetDto {
	
	@JsonProperty("type")
	protected String type;
	
	@JsonProperty("timeCompleted")
	protected Date timeCompleted;
	
	@JsonProperty("timeRested")
	protected Float timeRested;
	
	@JsonProperty("minimumRestTimeAllowed")
	protected Float minimumRestTimeAllowed;
	
	@JsonProperty("maximumRestTimeAllowed")
	protected Float maximumRestTimeAllowed;
	
	@JsonProperty("exercises")
	protected List<ExerciseDto> exercises;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

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
