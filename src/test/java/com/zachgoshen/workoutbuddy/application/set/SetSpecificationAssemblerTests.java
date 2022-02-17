package com.zachgoshen.workoutbuddy.application.set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.exercise.ExerciseSearchFilterDto;
import com.zachgoshen.workoutbuddy.application.set.SetSearchCriteriaDto;
import com.zachgoshen.workoutbuddy.application.set.SetSpecificationAssembler;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;

public class SetSpecificationAssemblerTests {
	
	@Test
	public void Assemble_CriteriaHasExerciseFilter_SpecificationIsSatisfiedBySetThatContainsSatisfyingExercise() {
		SetSearchCriteriaDto criteria = new SetSearchCriteriaDto();
		
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setName("Bench Press");
		criteria.setExerciseFilters(Arrays.asList(filter));
		
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		assertTrue(specification.isSatisfiedBy(set));
	}
	
	@Test
	public void Assemble_CriteriaHasExerciseFilter_SpecificationIsNotSatisfiedBySetThatDoesntContainSatisfyingExercise() {
		SetSearchCriteriaDto criteria = new SetSearchCriteriaDto();
		
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setName("Bench Press");
		criteria.setExerciseFilters(Arrays.asList(filter));
		
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		
		ExerciseDescription description = new ExerciseDescription("Deadlift");
		Exercise exercise = new Exercise(description);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		assertFalse(specification.isSatisfiedBy(set));
	}
	
	@Test
	public void Assemble_CriteriaHasMinimumTimeRestedCriterion_SpecificationIsSatisfiedBySetWithTimeRestedGreaterThanMinimum() {
		SetSearchCriteriaDto criteria = new SetSearchCriteriaDto();
		criteria.setMinimumTimeRested(60f);
		
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeRested(70f);
		
		assertTrue(specification.isSatisfiedBy(set));
	}
	
	@Test
	public void Assemble_CriteriaHasMinimumTimeRestedCriterion_SpecificationIsNotSatisfiedBySetWithTimeRestedLessThanMinimum() {
		SetSearchCriteriaDto criteria = new SetSearchCriteriaDto();
		criteria.setMinimumTimeRested(60f);
		
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeRested(50f);
		
		assertFalse(specification.isSatisfiedBy(set));
	}
	
	@Test
	public void Assemble_CriteriaHasMaximumTimeRestedCriterion_SpecificationIsSatisfiedBySetWithTimeRestedLessThanMaximum() {
		SetSearchCriteriaDto criteria = new SetSearchCriteriaDto();
		criteria.setMaximumTimeRested(60f);
		
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeRested(50f);
		
		assertTrue(specification.isSatisfiedBy(set));
	}
	
	@Test
	public void Assemble_CriteriaHasMaximumTimeRestedCriterion_SpecificationIsNotSatisfiedBySetWithTimeRestedGreaterThanMaximum() {
		SetSearchCriteriaDto criteria = new SetSearchCriteriaDto();
		criteria.setMaximumTimeRested(60f);
		
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeRested(70f);
		
		assertFalse(specification.isSatisfiedBy(set));
	}

}
