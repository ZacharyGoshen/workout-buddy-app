package com.zachgoshen.workouttracker.domain.workout.set;

import java.util.List;

import com.zachgoshen.workouttracker.domain.common.specification.AlwaysSatisfiedSpecification;
import com.zachgoshen.workouttracker.domain.common.specification.NeverSatisfiedSpecification;
import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.workout.set.specification.ContainsExerciseSpecification;

public class SetSpecifications {
	
	public static Specification<Set> alwaysSatisfied() {
		return new AlwaysSatisfiedSpecification<>();
	}
	
	public static Specification<Set> neverSatisfied() {
		return new NeverSatisfiedSpecification<>();
	}
	
	public static Specification<Set> containsExercise(String exerciseName) {
		return new ContainsExerciseSpecification(exerciseName);
	}
	
	public static Specification<Set> containsAtLeastOneExercise(List<String> exerciseNames) {
		return exerciseNames.stream()
			.map(
				exerciseName -> containsExercise(exerciseName))
			.reduce(
				neverSatisfied(), 
				(firstSpecification, secondSpecification) -> firstSpecification.or(secondSpecification));
	}

}
