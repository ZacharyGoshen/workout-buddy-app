package com.zachgoshen.workouttracker.application.workout.crud;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.workout.NonexistentWorkoutException;
import com.zachgoshen.workouttracker.application.workout.WorkoutConverter;
import com.zachgoshen.workouttracker.application.workout.WorkoutDto;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;
import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.WorkoutRepository;

@Service
public class WorkoutCreationApplicationService {
	
	private final WorkoutRepository repository;
	
	private WorkoutCreationApplicationService(WorkoutRepository repository) {
		this.repository = repository;
	}

	public String createAndReturnId(WorkoutDto dto) throws DtoConversionException, InvalidRangeException {
		Workout workout = WorkoutConverter.toEntity(dto);
		
		repository.save(workout);
		
		return workout.getId();
	}

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
