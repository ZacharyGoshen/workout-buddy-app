package com.zachgoshen.workouttracker.data.workout.plan;

import com.zachgoshen.workouttracker.data.DataConverter;
import com.zachgoshen.workouttracker.data.workout.exercise.ExerciseConverter;
import com.zachgoshen.workouttracker.data.workout.exercise.ExerciseData;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanItem;

public class WorkoutPlanItemConverter implements DataConverter<WorkoutPlanItem, WorkoutPlanItemData> {
	
	private final ExerciseConverter exerciseConverter = new ExerciseConverter();

	public WorkoutPlanItemData convertFromModel(WorkoutPlanItem model) {
		ExerciseData exerciseData = exerciseConverter.convertFromModel(model.getExercise());
		
		WorkoutPlanItemData itemData = new WorkoutPlanItemData();
		itemData.setId(model.getId());
		itemData.setIndex(model.getIndex());
		itemData.setExercise(exerciseData);
		itemData.setRepRangeMin(model.getRepRangeLowerBound());
		itemData.setRepRangeMax(model.getRepRangeUpperBound());
		itemData.setSecondsToRest(model.getSecondsToRest());
		
		return itemData;
	}

	public WorkoutPlanItem convertFromData(WorkoutPlanItemData data) {
		Exercise exercise = exerciseConverter.convertFromData(data.getExercise());
		
		return new WorkoutPlanItem(
			data.getId(), 
			data.getIndex(), 
			data.getRepRangeMin(), 
			data.getRepRangeMax(), 
			data.getSecondsToRest(), 
			exercise
		);
	}

}
