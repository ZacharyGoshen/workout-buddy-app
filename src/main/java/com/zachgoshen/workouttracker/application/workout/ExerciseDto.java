package com.zachgoshen.workouttracker.application.workout;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExerciseDto {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("weightUsed")
	private Float weightUsed;
	
	@JsonProperty("minimumWeightAllowed")
	private Float minimumWeightAllowed;
	
	@JsonProperty("maximumWeightAllowed")
	private Float maximumWeightAllowed;
	
	@JsonProperty("repsCompleted")
	private Integer repsCompleted;
	
	@JsonProperty("minimumRepsAllowed")
	private Integer minimumRepsAllowed;
	
	@JsonProperty("maximumRepsAllowed")
	private Integer maximumRepsAllowed;
	
	@JsonProperty("timePerformed")
	private Float timePerformed;
	
	@JsonProperty("minimumDurationAllowed")
	private Float minimumDurationAllowed;
	
	@JsonProperty("maximumDurationAllowed")
	private Float maximumDurationAllowed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getWeightUsed() {
		return weightUsed;
	}

	public void setWeightUsed(Float weightUsed) {
		this.weightUsed = weightUsed;
	}

	public Float getMinimumWeightAllowed() {
		return minimumWeightAllowed;
	}

	public void setMinimumWeightAllowed(Float minimumWeightAllowed) {
		this.minimumWeightAllowed = minimumWeightAllowed;
	}

	public Float getMaximumWeightAllowed() {
		return maximumWeightAllowed;
	}

	public void setMaximumWeightAllowed(Float maximumWeightAllowed) {
		this.maximumWeightAllowed = maximumWeightAllowed;
	}

	public Integer getRepsCompleted() {
		return repsCompleted;
	}

	public void setRepsCompleted(Integer repsCompleted) {
		this.repsCompleted = repsCompleted;
	}

	public Integer getMinimumRepsAllowed() {
		return minimumRepsAllowed;
	}

	public void setMinimumRepsAllowed(Integer minimumRepsAllowed) {
		this.minimumRepsAllowed = minimumRepsAllowed;
	}

	public Integer getMaximumRepsAllowed() {
		return maximumRepsAllowed;
	}

	public void setMaximumRepsAllowed(Integer maximumRepsAllowed) {
		this.maximumRepsAllowed = maximumRepsAllowed;
	}

	public Float getTimePerformed() {
		return timePerformed;
	}

	public void setTimePerformed(Float timePerformed) {
		this.timePerformed = timePerformed;
	}

	public Float getMinimumDurationAllowed() {
		return minimumDurationAllowed;
	}

	public void setMinimumDurationAllowed(Float minimumDurationAllowed) {
		this.minimumDurationAllowed = minimumDurationAllowed;
	}

	public Float getMaximumDurationAllowed() {
		return maximumDurationAllowed;
	}

	public void setMaximumDurationAllowed(Float maximumDurationAllowed) {
		this.maximumDurationAllowed = maximumDurationAllowed;
	}

}
