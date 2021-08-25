package com.zachgoshen.workouttracker.domain.workout;

public class WorkoutHeader {
	
	private String name;
	
	public WorkoutHeader(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
