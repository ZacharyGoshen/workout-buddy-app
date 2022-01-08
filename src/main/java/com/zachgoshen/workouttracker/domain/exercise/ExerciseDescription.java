package com.zachgoshen.workouttracker.domain.exercise;

import java.util.UUID;

public class ExerciseDescription {

	private final String id;
	private final String name;
	
	public ExerciseDescription(String name) {
		id = UUID.randomUUID().toString();
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public String getName() {
		return name;
	}

}
