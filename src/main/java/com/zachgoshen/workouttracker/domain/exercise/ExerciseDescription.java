package com.zachgoshen.workouttracker.domain.exercise;

import java.util.UUID;

public class ExerciseDescription {

	private final String id;
	private String name;
	
	public ExerciseDescription(String name) {
		this(UUID.randomUUID().toString(), name);
	}
	
	public ExerciseDescription(String id, String name) {
		this.id = id;
		this.name = name;
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

}
