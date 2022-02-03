package com.zachgoshen.workoutbuddy.domain.exercise.description;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class ExerciseDescription {

	private final String id;
	private String name;
	private List<MuscleGroup> muscleGroups;
	
	public ExerciseDescription(String name) {
		this.id = UUID.randomUUID().toString();
		this.name = name;
		this.muscleGroups = new ArrayList<>();
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<MuscleGroup> getMuscleGroups() {
		return muscleGroups;
	}

	public void setMuscleGroups(List<MuscleGroup> muscleGroups) {
		this.muscleGroups = muscleGroups;
	}
	
	public void addMuscleGroup(MuscleGroup muscleGroup) {
		if (!muscleGroups.contains(muscleGroup)) {
			muscleGroups.add(muscleGroup);
		}
	}
	
	public void removeMuscleGroup(MuscleGroup muscleGroup) {
		muscleGroups.remove(muscleGroup);
	}

}
