package com.zachgoshen.workouttracker.application.set;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zachgoshen.workouttracker.application.exercise.ExerciseSearchFilterDto;

public class SetSearchCriteriaDto {
	
	@JsonProperty("exerciseFilters")
	private List<ExerciseSearchFilterDto> exerciseFilters;
	
	@JsonProperty("minimumTimeRested")
	private Float minimumTimeRested;
	
	@JsonProperty("maximumTimeRested")
	private Float maximumTimeRested;

	public List<ExerciseSearchFilterDto> getExerciseFilters() {
		return exerciseFilters;
	}

	public void setExerciseFilters(List<ExerciseSearchFilterDto> exerciseSearchCriteriaDtos) {
		this.exerciseFilters = exerciseSearchCriteriaDtos;
	}

	public Float getMinimumTimeRested() {
		return minimumTimeRested;
	}

	public void setMinimumTimeRested(Float minimumTimeRested) {
		this.minimumTimeRested = minimumTimeRested;
	}

	public Float getMaximumTimeRested() {
		return maximumTimeRested;
	}

	public void setMaximumTimeRested(Float maximumTimeRested) {
		this.maximumTimeRested = maximumTimeRested;
	}

}
