package com.zachgoshen.workoutbuddy.application.exercise;

@SuppressWarnings("serial")
public class NonexistentExerciseDescriptionException extends Exception {
	
	private final String id;
	
	public NonexistentExerciseDescriptionException(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

}
