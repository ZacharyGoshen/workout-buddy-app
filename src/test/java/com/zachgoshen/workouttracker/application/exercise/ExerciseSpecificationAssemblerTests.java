package com.zachgoshen.workouttracker.application.exercise;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.exercise.ExerciseSearchFilterDto;
import com.zachgoshen.workouttracker.application.exercise.ExerciseSpecificationAssembler;
import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

public class ExerciseSpecificationAssemblerTests {
	
	@Test
	public void Assemble_FilterHasNameCriterion_SpecificationIsSatisfiedByExerciseWithSameName() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setName("Bench Press");
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		assertTrue(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasNameCriterion_SpecificationIsNotSatisfiedByExerciseWithDifferentName() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setName("Bench Press");
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Deadlift");
		Exercise exercise = new Exercise(description);
		
		assertFalse(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMinimumWeightUsedCriterion_SpecificationIsSatisfiedByExerciseWithWeightUsedGreaterThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMinimumWeightUsed(100f);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(105f);
		
		assertTrue(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMinimumWeightUsedCriterion_SpecificationIsNotSatisfiedByExerciseWithWeightUsedLessThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMinimumWeightUsed(100f);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(95f);
		
		assertFalse(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMaximumWeightUsedCriterion_SpecificationIsSatisfiedByExerciseWithWeightUsedLessThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMaximumWeightUsed(100f);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(95f);
		
		assertTrue(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMaximumWeightUsedCriterion_SpecificationIsNotSatisfiedByExerciseWithWeightUsedGreaterThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMaximumWeightUsed(100f);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(105f);
		
		assertFalse(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMinimumRepsCompletedCriterion_SpecificationIsSatisfiedByExerciseWithRepsCompletedGreaterThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMinimumRepsCompleted(5);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(6);
		
		assertTrue(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMinimumRepsCompletedCriterion_SpecificationIsNotSatisfiedByExerciseWithRepsCompletedLessThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMinimumRepsCompleted(5);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(4);
		
		assertFalse(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMaximumRepsCompletedCriterion_SpecificationIsSatisfiedByExerciseWithRepsCompletedLessThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMaximumRepsCompleted(5);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(4);
		
		assertTrue(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMaximumRepsCompletedCriterion_SpecificationIsNotSatisfiedByExerciseWithRepsCompletedGreaterThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMaximumRepsCompleted(5);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		exercise.setRepsCompleted(6);
		
		assertFalse(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMinimumTimePerformedCriterion_SpecificationIsSatisfiedByExerciseWithTimePerformedGreaterThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMinimumTimePerformed(60f);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(70f);
		
		assertTrue(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMinimumTimePerformedCriterion_SpecificationIsNotSatisfiedByExerciseWithTimePerformedLessThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMinimumTimePerformed(60f);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(50f);
		
		assertFalse(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMaximumTimePerformedCriterion_SpecificationIsSatisfiedByExerciseWithTimePerformedLessThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMaximumTimePerformed(60f);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(50f);
		
		assertTrue(specification.isSatisfiedBy(exercise));
	}
	
	@Test
	public void Assemble_FilterHasMaximumTimePerformedCriterion_SpecificationIsNotSatisfiedByExerciseWithTimePerformedGreaterThanMinimum() {
		ExerciseSearchFilterDto filter = new ExerciseSearchFilterDto();
		filter.setMaximumTimePerformed(60f);
		
		Specification<Exercise> specification = ExerciseSpecificationAssembler.assemble(filter);
		
		ExerciseDescription description = new ExerciseDescription("Plank");
		Exercise exercise = new Exercise(description);
		exercise.setTimePerformed(70f);
		
		assertFalse(specification.isSatisfiedBy(exercise));
	}

}
