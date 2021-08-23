package com.zachgoshen.workouttracker.data.workout.exercise;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.zachgoshen.workouttracker.data.workout.plan.WorkoutPlanItemData;

@Entity
@Table(schema = "workout_tracker", name = "exercise")
public class ExerciseData {
	
	@Id
	@GeneratedValue
	@Column(name = "id")
	private Long id;
	
	@Column(name = "name")
	private String name;
	
	@OneToMany(mappedBy = "exercise")
	private Set<WorkoutPlanItemData> workoutPlanItems;
	
	public ExerciseData() {}

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

	public Set<WorkoutPlanItemData> getWorkoutPlanItems() {
		return workoutPlanItems;
	}

	public void setWorkoutPlanItems(Set<WorkoutPlanItemData> workoutPlanItems) {
		this.workoutPlanItems = workoutPlanItems;
	}

}
