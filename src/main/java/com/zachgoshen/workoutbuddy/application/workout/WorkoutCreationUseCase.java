package com.zachgoshen.workoutbuddy.application.workout;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public interface WorkoutCreationUseCase {

	public String createAndReturnId(Workout workout);

	public String createNotCompletedCopyAndReturnId(String idOfWorkoutToCopy) throws NonexistentWorkoutException;

}
