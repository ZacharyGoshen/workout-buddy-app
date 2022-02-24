package com.zachgoshen.workoutbuddy.application.workout;

import java.util.Date;
import java.util.Optional;

public class WorkoutUpdate {
	
	private Optional<String> name;
	private Optional<Date> timeCompleted;
	
	public Optional<String> getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = Optional.ofNullable(name);
	}
	
	public Optional<Date> getTimeCompleted() {
		return timeCompleted;
	}
	
	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = Optional.ofNullable(timeCompleted);
	}

}
