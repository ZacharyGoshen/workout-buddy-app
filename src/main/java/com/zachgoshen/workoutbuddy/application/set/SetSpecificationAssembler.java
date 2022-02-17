package com.zachgoshen.workoutbuddy.application.set;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.zachgoshen.workoutbuddy.api.exercise.ExerciseSearchFilterDto;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseSpecificationAssembler;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.specification.SetSpecifications;

public class SetSpecificationAssembler {
	
	public static Specification<Set> assemble(SetSearchCriteriaDto criteria) {
		Specification<Set> specification = SetSpecifications.alwaysSatisfied();
		
		specification = addExerciseCriteriaToSpecification(criteria, specification);
		specification = addMinimumTimeRestedCriterionToSpecification(criteria, specification);
		specification = addMaximumTimeRestedCriterionToSpecification(criteria, specification);
		
		return specification;
	}
	
	private static Specification<Set> addExerciseCriteriaToSpecification(SetSearchCriteriaDto criteria, Specification<Set> specification) {
		List<Specification<Set>> containsSatisfyingExerciseSpecifications = buildContainsSatisfyingExerciseSpecifications(criteria);
		
		Specification<Set> containsSatisfyingExercisesSpecification = SetSpecifications.alwaysSatisfied();
		
		for (Specification<Set> containsSatisfyingExerciseSpecification : containsSatisfyingExerciseSpecifications) {
			containsSatisfyingExercisesSpecification = containsSatisfyingExercisesSpecification.and(containsSatisfyingExerciseSpecification);
		}
		
		return specification.and(containsSatisfyingExercisesSpecification);
	}
	
	private static List<Specification<Set>> buildContainsSatisfyingExerciseSpecifications(SetSearchCriteriaDto setCriteria) {
		List<ExerciseSearchFilterDto> exerciseFilters = setCriteria.getExerciseFilters();
		
		if (exerciseFilters == null) {
			return new ArrayList<>();
		} else {
			return exerciseFilters.stream()
				.map(exerciseCriteria -> ExerciseSpecificationAssembler.assemble(exerciseCriteria))
				.map(exerciseSpecification -> SetSpecifications.containsSatisfyingExercise(exerciseSpecification))
				.collect(Collectors.toList());
		}
	}
	
	private static Specification<Set> addMinimumTimeRestedCriterionToSpecification(SetSearchCriteriaDto criteria, Specification<Set> specification) {
		Float minimumTimeRested = criteria.getMinimumTimeRested();
		
		if (minimumTimeRested == null) {
			return specification;
		} else {
			return specification.and(SetSpecifications.timeRestedIsAtLeast(minimumTimeRested));
		}
	}
	
	private static Specification<Set> addMaximumTimeRestedCriterionToSpecification(SetSearchCriteriaDto criteria, Specification<Set> specification) {
		Float maximumTimeRested = criteria.getMaximumTimeRested();
		
		if (maximumTimeRested == null) {
			return specification;
		} else {
			return specification.and(SetSpecifications.timeRestedIsAtMost(maximumTimeRested));
		}
	}

}
