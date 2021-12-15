package com.zachgoshen.workouttracker.application.set;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonProperty;

public class SetWithWorkoutDetailsDto extends SetDto {
	
	public SetWithWorkoutDetailsDto() {
	}
	
	public SetWithWorkoutDetailsDto(SetDto setDto) {
		this.type = setDto.getType();
		this.timeCompleted = setDto.getTimeCompleted();
		this.timeRested = setDto.getTimeRested();
		this.minimumRestTimeAllowed = setDto.getMinimumRestTimeAllowed();
		this.maximumRestTimeAllowed = setDto.getMaximumRestTimeAllowed();
		this.exercises = setDto.getExercises();
	}
	
	@JsonProperty("workoutId")
	private String workoutId;
	
	@JsonProperty("workoutName")
	private String workoutName;
	
	@JsonProperty("workoutCompletionTime")
	private Date workoutCompletionTime;
	
	@JsonProperty("setNumber")
	private Integer setNumber;

	public String getWorkoutId() {
		return workoutId;
	}

	public void setWorkoutId(String workoutId) {
		this.workoutId = workoutId;
	}

	public String getWorkoutName() {
		return workoutName;
	}

	public void setWorkoutName(String workoutName) {
		this.workoutName = workoutName;
	}

	public Date getWorkoutCompletionTime() {
		return workoutCompletionTime;
	}

	public void setWorkoutCompletionTime(Date workoutCompletionTime) {
		this.workoutCompletionTime = workoutCompletionTime;
	}

	public Integer getSetNumber() {
		return setNumber;
	}

	public void setSetNumber(Integer setNumber) {
		this.setNumber = setNumber;
	}

}
