package com.zachgoshen.workouttracker.data.workout.plan;

import com.zachgoshen.workouttracker.data.DataConverter;
import com.zachgoshen.workouttracker.data.workout.exercise.ExerciseConverter;
import com.zachgoshen.workouttracker.data.workout.exercise.ExerciseData;
import com.zachgoshen.workouttracker.domain.workout.WorkoutItemHeader;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanItem;

public class WorkoutPlanItemConverter implements DataConverter<WorkoutPlanItem, WorkoutPlanItemData> {
	
	private static final ExerciseConverter exerciseConverter = new ExerciseConverter();

	public WorkoutPlanItemData convertFromModel(WorkoutPlanItem model) {
		ExerciseData exerciseData = exerciseConverter.convertFromModel(model.getHeader().getExercise());
		
		WorkoutPlanItemData itemData = new WorkoutPlanItemData();
		itemData.setId(model.getId());
		itemData.setIndex(model.getHeader().getIndex());
		itemData.setExercise(exerciseData);
		itemData.setRepRangeMin(model.getHeader().getRepRangeLowerBound());
		itemData.setRepRangeMax(model.getHeader().getRepRangeUpperBound());
		itemData.setSecondsToRest(model.getHeader().getSecondsToRest());
		return itemData;
	}

	public WorkoutPlanItem convertFromData(WorkoutPlanItemData data) {
		WorkoutItemHeader itemHeader = buildItemHeader(data);
		return new WorkoutPlanItem(data.getId(), itemHeader);
	}
	
	private static WorkoutItemHeader buildItemHeader(WorkoutPlanItemData data) {
		Exercise exercise = exerciseConverter.convertFromData(data.getExercise());
		
		return new WorkoutItemHeader(
			data.getIndex(), 
			data.getRepRangeMin(), 
			data.getRepRangeMax(), 
			data.getSecondsToRest(), 
			exercise);
	}

}
