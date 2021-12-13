package com.zachgoshen.workouttracker.application.set;

import java.util.List;

public class SetQueryParameters {
	
	private List<String> exerciseNames;

	public SetQueryParameters(List<String> exerciseNames) {
		this.exerciseNames = exerciseNames;
	}

	public List<String> getExerciseNames() {
		return exerciseNames;
	}

}