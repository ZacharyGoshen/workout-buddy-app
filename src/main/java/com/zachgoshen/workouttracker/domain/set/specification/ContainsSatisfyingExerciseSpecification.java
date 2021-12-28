package com.zachgoshen.workouttracker.domain.set.specification;

import java.util.List;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;

public class ContainsSatisfyingExerciseSpecification extends Specification<Set> {
	
	private final Specification<Exercise> exerciseSpecification;

	public ContainsSatisfyingExerciseSpecification(Specification<Exercise> exerciseSpecification) {
		this.exerciseSpecification = exerciseSpecification;
	}

	@Override
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
		Exercise exercise = candidate.getExercise();
		
		return exerciseSpecification.isSatisfiedBy(exercise);
	}
	
	private boolean isSatisfiedBy(Superset candidate) {
		List<Exercise> exercises = candidate.getExercises();
		
		for (Exercise exercise : exercises) {
			if (exerciseSpecification.isSatisfiedBy(exercise)) {
				return true;
			}
		}
		
		return false;
	}

}
