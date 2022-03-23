package com.zachgoshen.workoutbuddy.api.set;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseSearchFilterDto;

public class SetSearchCriteriaDto {
	
	@JsonProperty("sortBy")
	private String sortBy;
	
	@JsonProperty("minimumTimeRested")
	private Float minimumTimeRested;
	
	@JsonProperty("maximumTimeRested")
	private Float maximumTimeRested;
	
	@JsonProperty("exerciseFilters")
	private List<ExerciseSearchFilterDto> exerciseFilters;

	public String getSortBy() {
		return sortBy;
	}

	public void setSortBy(String sortBy) {
		this.sortBy = sortBy;
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

	public List<ExerciseSearchFilterDto> getExerciseFilters() {
		return exerciseFilters;
	}

	public void setExerciseFilters(List<ExerciseSearchFilterDto> exerciseSearchCriteriaDtos) {
		this.exerciseFilters = exerciseSearchCriteriaDtos;
	}

}
