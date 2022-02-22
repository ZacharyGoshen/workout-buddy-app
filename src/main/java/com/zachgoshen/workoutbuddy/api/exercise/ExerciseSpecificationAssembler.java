package com.zachgoshen.workoutbuddy.api.exercise;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.specification.ExerciseSpecifications;

public final class ExerciseSpecificationAssembler {
	
	private ExerciseSpecificationAssembler() {}
	
	public static Specification<Exercise> assemble(ExerciseSearchFilterDto filter) {
		Specification<Exercise> specification = ExerciseSpecifications.alwaysSatisfied();
		
		specification = addNameCriterionToSpecification(filter, specification);
		specification = addMinimumWeightUsedCriterionToSpecification(filter, specification);
		specification = addMaximumWeightUsedCriterionToSpecification(filter, specification);
		specification = addMinimumRepsCompletedCriterionToSpecification(filter, specification);
		specification = addMaximumRepsCompletedCriterionToSpecification(filter, specification);
		specification = addMinimumTimePerformedCriterionToSpecification(filter, specification);
		specification = addMaximumTimePerformedCriterionToSpecification(filter, specification);
		
		return specification;
	}
	
	private static Specification<Exercise> addNameCriterionToSpecification(ExerciseSearchFilterDto filter, Specification<Exercise> specification) {
		String name = filter.getName();
		
		if (name == null) {
			return specification;
		} else {
			return specification.and(ExerciseSpecifications.nameIs(name));
		}
	}
	
	private static Specification<Exercise> addMinimumWeightUsedCriterionToSpecification(ExerciseSearchFilterDto filter, Specification<Exercise> specification) {
		Float minimumWeightUsed = filter.getMinimumWeightUsed();
		
		if (minimumWeightUsed == null) {
			return specification;
		} else {
			return specification.and(ExerciseSpecifications.weightUsedIsAtLeast(minimumWeightUsed));
		}
	}
	
	private static Specification<Exercise> addMaximumWeightUsedCriterionToSpecification(ExerciseSearchFilterDto filter, Specification<Exercise> specification) {
		Float maximumWeightUsed = filter.getMaximumWeightUsed();
		
		if (maximumWeightUsed == null) {
			return specification;
		} else {
			return specification.and(ExerciseSpecifications.weightUsedIsAtMost(maximumWeightUsed));
		}
	}
	
	private static Specification<Exercise> addMinimumRepsCompletedCriterionToSpecification(ExerciseSearchFilterDto filter, Specification<Exercise> specification) {
		Integer minimumRepsCompleted = filter.getMinimumRepsCompleted();
		
		if (minimumRepsCompleted == null) {
			return specification;
		} else {
			return specification.and(ExerciseSpecifications.repsCompletedIsAtLeast(minimumRepsCompleted));
		}
	}
	
	private static Specification<Exercise> addMaximumRepsCompletedCriterionToSpecification(ExerciseSearchFilterDto filter, Specification<Exercise> specification) {
		Integer maximumRepsCompleted = filter.getMaximumRepsCompleted();
		
		if (maximumRepsCompleted == null) {
			return specification;
		} else {
			return specification.and(ExerciseSpecifications.repsCompletedIsAtMost(maximumRepsCompleted));
		}
	}
	
	private static Specification<Exercise> addMinimumTimePerformedCriterionToSpecification(ExerciseSearchFilterDto filter, Specification<Exercise> specification) {
		Float minimumTimePerformed = filter.getMinimumTimePerformed();
		
		if (minimumTimePerformed == null) {
			return specification;
		} else {
			return specification.and(ExerciseSpecifications.timePerformedIsAtLeast(minimumTimePerformed));
		}
	}
	
	private static Specification<Exercise> addMaximumTimePerformedCriterionToSpecification(ExerciseSearchFilterDto filter, Specification<Exercise> specification) {
		Float maximumTimePerformed = filter.getMaximumTimePerformed();
		
		if (maximumTimePerformed == null) {
			return specification;
		} else {
			return specification.and(ExerciseSpecifications.timePerformedIsAtMost(maximumTimePerformed));
		}
	}

}
