package com.zachgoshen.workouttracker.data.workout.exercise;

import com.zachgoshen.workouttracker.data.DataConverter;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;

public class ExerciseConverter implements DataConverter<Exercise, ExerciseData> {

	public ExerciseData convertFromModel(Exercise model) {
		ExerciseData data = new ExerciseData();
		data.setId(model.getId());
		data.setName(model.getName());
		return data;
	}

	public Exercise convertFromData(ExerciseData data) {
		return new Exercise(data.getId(), data.getName());
	}

}
