package com.zachgoshen.workoutbuddy.api.exercise;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExerciseDescriptionDto {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("notes")
	private String notes;
	
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

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public List<String> getMuscleGroups() {
		return muscleGroups;
	}

	public void setMuscleGroups(List<String> muscleGroups) {
		this.muscleGroups = muscleGroups;
	}

}
