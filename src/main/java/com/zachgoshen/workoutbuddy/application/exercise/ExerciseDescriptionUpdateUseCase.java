package com.zachgoshen.workoutbuddy.application.exercise;

public interface ExerciseDescriptionUpdateUseCase {

	public void updateById(String id, ExerciseDescriptionUpdate modification) throws NonexistentExerciseDescriptionException;

}
