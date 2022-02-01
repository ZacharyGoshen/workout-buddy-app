package com.zachgoshen.workoutbuddy.domain.exercise.specification;

import com.zachgoshen.workoutbuddy.domain.common.specification.AlwaysSatisfiedSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;

public class ExerciseSpecifications {
	
	public static Specification<Exercise> alwaysSatisfied() {
		return new AlwaysSatisfiedSpecification<>();
	}
	
	public static Specification<Exercise> nameIs(String name) {
		return new NameSpecification(name);
	}
	
	public static Specification<Exercise> weightUsedIsAtLeast(Float weightUsed) {
		return new MinimumWeightUsedSpecification(weightUsed);
	}
	
	public static Specification<Exercise> weightUsedIsAtMost(Float weightUsed) {
		return new MaximumWeightUsedSpecification(weightUsed);
	}
	
	public static Specification<Exercise> repsCompletedIsAtLeast(Integer repsCompleted) {
		return new MinimumRepsCompletedSpecification(repsCompleted);
	}
	
	public static Specification<Exercise> repsCompletedIsAtMost(Integer repsCompleted) {
		return new MaximumRepsCompletedSpecification(repsCompleted);
	}
	
	public static Specification<Exercise> timePerformedIsAtLeast(Float timePerformed) {
		return new MinimumTimePerformedSpecification(timePerformed);
	}
	
	public static Specification<Exercise> timePerformedIsAtMost(Float timePerformed) {
		return new MaximumTimePerformedSpecification(timePerformed);
	}

}
