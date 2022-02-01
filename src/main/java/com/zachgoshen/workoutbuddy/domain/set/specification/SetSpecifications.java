package com.zachgoshen.workoutbuddy.domain.set.specification;

import com.zachgoshen.workoutbuddy.domain.common.specification.AlwaysSatisfiedSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.NeverSatisfiedSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.set.Set;

public class SetSpecifications {
	
	public static Specification<Set> alwaysSatisfied() {
		return new AlwaysSatisfiedSpecification<>();
	}
	
	public static Specification<Set> neverSatisfied() {
		return new NeverSatisfiedSpecification<>();
	}
	
	public static Specification<Set> containsSatisfyingExercise(Specification<Exercise> exerciseSpecification) {
		return new ContainsSatisfyingExerciseSpecification(exerciseSpecification);
	}

	public static Specification<Set> timeRestedIsAtLeast(Float timeRested) {
		return new MinimumTimeRestedSpecification(timeRested);
	}
	
	public static Specification<Set> timeRestedIsAtMost(Float timeRested) {
		return new MaximumTimeRestedSpecification(timeRested);
	}
	
}
