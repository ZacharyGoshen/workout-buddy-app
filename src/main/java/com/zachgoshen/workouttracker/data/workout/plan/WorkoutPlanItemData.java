package com.zachgoshen.workouttracker.data.workout.plan;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.zachgoshen.workouttracker.data.workout.exercise.ExerciseData;

@Entity
@Table(schema = "workout_tracker", name = "workout_plan_item")
public class WorkoutPlanItemData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "index")
	private Integer index;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "workout_plan_id", nullable = false)
	private WorkoutPlanData workoutPlan;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "exercise_id", nullable = false)
	private ExerciseData exercise;
	
	@Column(name = "rep_range_min")
	private Integer repRangeMin;
	
	@Column(name = "rep_range_max")
	private Integer repRangeMax;
	
	@Column(name = "seconds_to_rest")
	private Integer secondsToRest;
	
	public WorkoutPlanItemData() { }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public WorkoutPlanData getWorkoutPlan() {
		return workoutPlan;
	}

	public void setWorkoutPlan(WorkoutPlanData workoutPlan) {
		this.workoutPlan = workoutPlan;
	}

	public ExerciseData getExercise() {
		return exercise;
	}

	public void setExercise(ExerciseData exercise) {
		this.exercise = exercise;
	}

	public Integer getRepRangeMin() {
		return repRangeMin;
	}

	public void setRepRangeMin(Integer repRangeMin) {
		this.repRangeMin = repRangeMin;
	}

	public Integer getRepRangeMax() {
		return repRangeMax;
	}

	public void setRepRangeMax(Integer repRangeMax) {
		this.repRangeMax = repRangeMax;
	}

	public Integer getSecondsToRest() {
		return secondsToRest;
	}

	public void setSecondsToRest(Integer secondsToRest) {
		this.secondsToRest = secondsToRest;
	}

}
