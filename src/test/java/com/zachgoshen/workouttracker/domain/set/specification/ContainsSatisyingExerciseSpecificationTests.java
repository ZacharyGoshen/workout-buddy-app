package com.zachgoshen.workouttracker.domain.set.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.specification.ExerciseSpecifications;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;

public class ContainsSatisyingExerciseSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_SingleExerciseSetThatContainsSatisfyinExercise_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		Specification<Exercise> exerciseSpecification = ExerciseSpecifications.nameIs("Bench Press");
		Specification<Set> setSpecification = SetSpecifications.containsSatisfyingExercise(exerciseSpecification);
		
		boolean isSatisfied = setSpecification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SingleExerciseSetThatDoesntContainSatisfyingExercise_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		Specification<Exercise> exerciseSpecification = ExerciseSpecifications.nameIs("Squat");
		Specification<Set> setSpecification = SetSpecifications.containsSatisfyingExercise(exerciseSpecification);
		
		boolean isSatisfied = setSpecification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SupersetThatContainsSatisfyingExercise_ReturnsTrue() {
		ExerciseDescription description1 = new ExerciseDescription("Bench Press");
		Exercise exercise1 = new Exercise(description1);
		
		ExerciseDescription description2 = new ExerciseDescription("Dip");
		Exercise exercise2 = new Exercise(description2);
		
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
		
		Superset set = new Superset(exercises);
		
		Specification<Exercise> exerciseSpecification = ExerciseSpecifications.nameIs("Bench Press");
		Specification<Set> setSpecification = SetSpecifications.containsSatisfyingExercise(exerciseSpecification);
		
		boolean isSatisfied = setSpecification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SupersetThatDoesntContainSatisfyingExercise_ReturnsFalse() {
		ExerciseDescription description1 = new ExerciseDescription("Bench Press");
		Exercise exercise1 = new Exercise(description1);
		
		ExerciseDescription description2 = new ExerciseDescription("Dip");
		Exercise exercise2 = new Exercise(description2);
		
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
		
		Superset set = new Superset(exercises);
		
		Specification<Exercise> exerciseSpecification = ExerciseSpecifications.nameIs("Squat");
		Specification<Set> setSpecification = SetSpecifications.containsSatisfyingExercise(exerciseSpecification);
		
		boolean isSatisfied = setSpecification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}

}
