package com.zachgoshen.workouttracker.domain.workout.set;

import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;

public class ContainsExerciseSpecification extends Specification<Set> {
	
	private final String exerciseName;

	public ContainsExerciseSpecification(String exerciseName) {
		this.exerciseName = exerciseName;
	}

	public boolean isSatisfiedBy(Set candidate) {
		if (candidate instanceof SingleExerciseSet) {
			return isSatisfiedBy((SingleExerciseSet) candidate);
		} else if (candidate instanceof Superset) {
			return isSatisfiedBy((Superset) candidate);
		} else {
			return false;
		}
	}
	
	private boolean isSatisfiedBy(SingleExerciseSet candidate) {
		String candidateExerciseName = candidate.getExercise().getDescription().getName();
		
		return candidateExerciseName.equals(exerciseName);
	}
	
	private boolean isSatisfiedBy(Superset candidate) {
		List<String> candidateExerciseNames = candidate.getExercises()
			.stream()
			.map(exercise -> exercise.getDescription().getName())
			.collect(Collectors.toList());
		
		return candidateExerciseNames.contains(exerciseName);
	}

}
