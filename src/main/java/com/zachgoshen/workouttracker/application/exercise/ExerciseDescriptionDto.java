package com.zachgoshen.workouttracker.application.exercise;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExerciseDescriptionDto {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("muscleGroups")
	private List<String> muscleGroups; 

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getMuscleGroups() {
		return muscleGroups;
	}

	public void setMuscleGroups(List<String> muscleGroups) {
		this.muscleGroups = muscleGroups;
	}

}
