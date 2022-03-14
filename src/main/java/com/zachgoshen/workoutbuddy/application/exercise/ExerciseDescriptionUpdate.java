package com.zachgoshen.workoutbuddy.application.exercise;

import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public class ExerciseDescriptionUpdate {
	
	private Optional<String> name;
	private Optional<String> notes;
	private Optional<List<MuscleGroup>> muscleGroups;
	
	public Optional<String> getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = Optional.ofNullable(name);
	}
	
	public Optional<String> getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = Optional.ofNullable(notes);
	}

	public Optional<List<MuscleGroup>> getMuscleGroups() {
		return muscleGroups;
	}
	
	public void setMuscleGroups(List<MuscleGroup> muscleGroups) {
		this.muscleGroups = Optional.ofNullable(muscleGroups);
	}

}
