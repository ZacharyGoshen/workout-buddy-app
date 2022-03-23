package com.zachgoshen.workoutbuddy.application.workout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.Superset;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class WorkoutCreationService implements WorkoutCreationUseCase {
	
	private final WorkoutRepository repository;
	
	public WorkoutCreationService(WorkoutRepository repository) {
		this.repository = repository;
	}

	@Override
	public String createAndReturnId(Workout workout) {
		repository.save(workout);
		
		return workout.getId();
	}

	@Override
	public String createNotCompletedCopyAndReturnId(String idOfWorkoutToCopy) throws NonexistentWorkoutException {
		Workout workoutToCopy = tryToFindWorkoutById(idOfWorkoutToCopy);
		
		Workout workoutCopy = new Workout(workoutToCopy);
		
		workoutCopy.setTimeCompleted(null);
		
		for (Set set : workoutCopy.getSets()) {
			set.setTimeCompleted(null);
			set.setTimeRested(null);
			
			List<Exercise> exercises = new ArrayList<>();
			
			if (set instanceof SingleExerciseSet) {
				SingleExerciseSet singleExerciseSet = (SingleExerciseSet) set;
				Exercise exercise = singleExerciseSet.getExercise();
				exercises = Arrays.asList(exercise);
			} else if (set instanceof Superset) {
				Superset superset = (Superset) set;
				exercises = superset.getExercises();
			}
			
			for (Exercise exercise : exercises) {
				exercise.setWeightUsed(null);
				exercise.setRepsCompleted(null);
				exercise.setTimePerformed(null);
			}
		}
		
		repository.save(workoutCopy);
		
		return workoutCopy.getId();
	}
	
	private Workout tryToFindWorkoutById(String id) throws NonexistentWorkoutException {
		Optional<Workout> workoutOptional = repository.findById(id);
		
		if (workoutOptional.isPresent()) {
			return workoutOptional.get();
		} else {
			throw new NonexistentWorkoutException();
		}
	}
	
}
