package com.zachgoshen.workouttracker.application.workout;

import java.util.List;

public class QuerySetsParameters {
	
	private List<String> exerciseNames;

	public QuerySetsParameters(List<String> exerciseNames) {
		this.exerciseNames = exerciseNames;
	}

	public List<String> getExerciseNames() {
		return exerciseNames;
	}

}
