package com.zachgoshen.workouttracker.application.workout;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.zachgoshen.workouttracker.application.set.SetDto;

public class WorkoutDto {
	
	@JsonProperty("id")
	private String id;
	
	@JsonProperty("name")
	private String name;
	
	@JsonProperty("timeCompleted")
	private Date timeCompleted;
	
	@JsonProperty("sets")
	private List<SetDto> sets;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public List<SetDto> getSets() {
		return sets;
	}

	public void setSets(List<SetDto> sets) {
		this.sets = sets;
	}

}
