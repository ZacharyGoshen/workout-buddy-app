package com.zachgoshen.workouttracker.data.workout.session;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zachgoshen.workouttracker.data.workout.plan.WorkoutPlanItemData;

@Entity
@Table(schema = "workout_tracker", name = "workout_session_item")
public class WorkoutSessionItemData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "reps_completed")
	private Integer repsCompleted;
	
	@Column(name = "weight_used")
	private Float weightUsed;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "workout_plan_item_id", nullable = false)
	private WorkoutPlanItemData workoutPlanItem;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "workout_session_id", nullable = false)
	private WorkoutSessionData workoutSession;
	
	public WorkoutSessionItemData() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getRepsCompleted() {
		return repsCompleted;
	}

	public void setRepsCompleted(Integer repsCompleted) {
		this.repsCompleted = repsCompleted;
	}

	public Float getWeightUsed() {
		return weightUsed;
	}

	public void setWeightUsed(Float weightUsed) {
		this.weightUsed = weightUsed;
	}

	public WorkoutPlanItemData getWorkoutPlanItem() {
		return workoutPlanItem;
	}

	public void setWorkoutPlanItem(WorkoutPlanItemData workoutPlanItem) {
		this.workoutPlanItem = workoutPlanItem;
	}

	public WorkoutSessionData getWorkoutSession() {
		return workoutSession;
	}

	public void setWorkoutSession(WorkoutSessionData workoutSession) {
		this.workoutSession = workoutSession;
	}

}
