package com.zachgoshen.workoutbuddy.api.exercise;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExerciseSearchFilterDto {
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("minimumWeightUsed")
	private Float minimumWeightUsed;
	
	@JsonProperty("maximumWeightUsed")
	private Float maximumWeightUsed;
	
	@JsonProperty("minimumRepsCompleted")
	private Integer minimumRepsCompleted;
	
	@JsonProperty("maximumRepsCompleted")
	private Integer maximumRepsCompleted;
	
	@JsonProperty("minimumTimePerformed")
	private Float minimumTimePerformed;
	
	@JsonProperty("maximumTimePerformed")
	private Float maximumTimePerformed;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Float getMinimumWeightUsed() {
		return minimumWeightUsed;
	}

	public void setMinimumWeightUsed(Float minimumWeightUsed) {
		this.minimumWeightUsed = minimumWeightUsed;
	}

	public Float getMaximumWeightUsed() {
		return maximumWeightUsed;
	}

	public void setMaximumWeightUsed(Float maximumWeightUsed) {
		this.maximumWeightUsed = maximumWeightUsed;
	}

	public Integer getMinimumRepsCompleted() {
		return minimumRepsCompleted;
	}

	public void setMinimumRepsCompleted(Integer minimumRepsCompleted) {
		this.minimumRepsCompleted = minimumRepsCompleted;
	}

	public Integer getMaximumRepsCompleted() {
		return maximumRepsCompleted;
	}

	public void setMaximumRepsCompleted(Integer maximumRepsCompleted) {
		this.maximumRepsCompleted = maximumRepsCompleted;
	}

	public Float getMinimumTimePerformed() {
		return minimumTimePerformed;
	}

	public void setMinimumTimePerformed(Float minimumTimePerformed) {
		this.minimumTimePerformed = minimumTimePerformed;
	}

	public Float getMaximumTimePerformed() {
		return maximumTimePerformed;
	}

	public void setMaximumTimePerformed(Float maximumTimePerformed) {
		this.maximumTimePerformed = maximumTimePerformed;
	}

}
