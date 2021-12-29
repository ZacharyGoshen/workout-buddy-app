package com.zachgoshen.workouttracker.domain.application.set;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.exercise.ExerciseSearchFilterDto;
import com.zachgoshen.workouttracker.application.set.SetSearchCriteriaDto;
import com.zachgoshen.workouttracker.application.set.SetSpecificationAssembler;
import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;

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
