package com.zachgoshen.workouttracker.domain.workout.session;

import java.util.Date;
import java.util.List;

public class WorkoutSession {
	
	private long id;
	private String name;
	private Date timeCompleted;
	private List<WorkoutSessionItem> items;
	
	public WorkoutSession(long id, String name, Date timeCompleted, List<WorkoutSessionItem> items) {
		this.id = id;
		this.name = name;
		this.timeCompleted = timeCompleted;
		this.items = items;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = timeCompleted;
	}

	public List<WorkoutSessionItem> getItems() {
		return items;
	}

	public void setItems(List<WorkoutSessionItem> items) {
		this.items = items;
	}

}
