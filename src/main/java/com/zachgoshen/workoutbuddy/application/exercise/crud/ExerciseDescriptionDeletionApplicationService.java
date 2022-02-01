package com.zachgoshen.workoutbuddy.application.exercise.crud;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.application.exercise.UndeletableExerciseDescriptionException;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.exercise.specification.ExerciseSpecifications;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SetRepository;
import com.zachgoshen.workoutbuddy.domain.set.specification.SetSpecifications;

@Service
public class ExerciseDescriptionDeletionApplicationService {
	
	private final ExerciseDescriptionRepository exerciseDescriptionRepository;
	private final SetRepository setRepository;
	
	public ExerciseDescriptionDeletionApplicationService(
			ExerciseDescriptionRepository exerciseDescriptionRepository, 
			SetRepository setRepository) {
		
		this.exerciseDescriptionRepository = exerciseDescriptionRepository;
		this.setRepository = setRepository;
	}
	
	public void deleteById(String id) throws UndeletableExerciseDescriptionException {
		boolean exerciseDescriptionIsUsedByAtLeastOneWorkout = isExerciseDescriptionUsedByAnyWorkouts(id);
		if (exerciseDescriptionIsUsedByAtLeastOneWorkout) {
			throw new UndeletableExerciseDescriptionException();
		}
		
		exerciseDescriptionRepository.deleteById(id);
	}
	
	private boolean isExerciseDescriptionUsedByAnyWorkouts(String id) {
		Optional<ExerciseDescription> description = exerciseDescriptionRepository.findById(id);
		
		if (description.isPresent()) {
			String exerciseName = description.get().getName();
			List<Set> sets = findAllSetsContaingExerciseWithName(exerciseName);
			return !sets.isEmpty();
		} else {
			return false;
		}
	}
	
	private List<Set> findAllSetsContaingExerciseWithName(String exerciseName) {
		Specification<Exercise> exerciseNameSpecification = ExerciseSpecifications.nameIs(exerciseName);
		Specification<Set> setSpecification = SetSpecifications.containsSatisfyingExercise(exerciseNameSpecification);
		
		return setRepository.findBy(setSpecification);
	}

}
