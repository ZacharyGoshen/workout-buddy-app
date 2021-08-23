package com.zachgoshen.workouttracker.data.workout.plan;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import com.zachgoshen.workouttracker.data.workout.session.WorkoutSessionData;

@Entity
@Table(schema = "workout_tracker", name = "workout_plan")
public class WorkoutPlanData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;

	@Column(name = "name")
	private String name;

	@OrderBy("index ASC")
	@OneToMany(mappedBy = "workoutPlan")
	private List<WorkoutPlanItemData> workoutPlanItems;
	
	@OneToMany(mappedBy = "workoutPlan")
	private List<WorkoutSessionData> workoutSessions;
	
	public WorkoutPlanData() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<WorkoutPlanItemData> getWorkoutPlanItems() {
		return workoutPlanItems;
	}

	public void setWorkoutPlanItems(List<WorkoutPlanItemData> workoutPlanItems) {
		this.workoutPlanItems = workoutPlanItems;
	}

	public List<WorkoutSessionData> getWorkoutSessions() {
		return workoutSessions;
	}

	public void setWorkoutSessions(List<WorkoutSessionData> workoutSessions) {
		this.workoutSessions = workoutSessions;
	}
	
}
