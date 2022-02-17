package com.zachgoshen.workoutbuddy.application.exercise;

public interface ExerciseDescriptionDeletionUseCase {

	void deleteById(String id) throws UndeletableExerciseDescriptionException;

}
