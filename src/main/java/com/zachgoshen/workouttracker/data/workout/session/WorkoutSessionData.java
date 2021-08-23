package com.zachgoshen.workouttracker.data.workout.session;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zachgoshen.workouttracker.data.workout.plan.WorkoutPlanData;

@Entity
@Table(schema = "workout_tracker", name = "workout_session")
public class WorkoutSessionData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "time_completed")
	private Date timeCompleted;

	@ManyToOne(optional = false)
	private WorkoutPlanData workoutPlan;
	
	@OneToMany(mappedBy = "workoutSession")
	private List<WorkoutSessionItemData> workoutSessionItems;
	
	public WorkoutSessionData() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = timeCompleted;
	}

	public WorkoutPlanData getWorkoutPlan() {
		return workoutPlan;
	}

	public void setWorkoutPlan(WorkoutPlanData workoutPlan) {
		this.workoutPlan = workoutPlan;
	}

	public List<WorkoutSessionItemData> getWorkoutSessionItems() {
		return workoutSessionItems;
	}

	public void setWorkoutSessionItems(List<WorkoutSessionItemData> workoutSessionItems) {
		this.workoutSessionItems = workoutSessionItems;
	}
	
}
