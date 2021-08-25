package com.zachgoshen.workouttracker.domain.workout.session;

import java.util.Date;
import java.util.List;

import com.zachgoshen.workouttracker.domain.workout.WorkoutHeader;

public class WorkoutSession {
	
	private long id;
	private WorkoutHeader header;
	private Date timeCompleted;
	private List<WorkoutSessionItem> items;
	
	public WorkoutSession(long id, WorkoutHeader header, Date timeCompleted, List<WorkoutSessionItem> items) {
		this.id = id;
		this.header = header;
		this.timeCompleted = timeCompleted;
		this.items = items;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public WorkoutHeader getHeader() {
		return header;
	}

	public void setHeader(WorkoutHeader header) {
		this.header = header;
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
