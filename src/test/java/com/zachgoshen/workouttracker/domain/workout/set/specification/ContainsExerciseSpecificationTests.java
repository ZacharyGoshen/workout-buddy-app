package com.zachgoshen.workouttracker.domain.workout.set.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.workout.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.workout.set.Superset;
import com.zachgoshen.workouttracker.domain.workout.set.specification.ContainsExerciseSpecification;

public class ContainsExerciseSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_SingleExerciseSetThatContainsExercise_ReturnsTrue() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		ContainsExerciseSpecification specification = new ContainsExerciseSpecification("Bench Press");
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SingleExerciseSetThatDoesntContainExercise_ReturnsFalse() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		ContainsExerciseSpecification specification = new ContainsExerciseSpecification("Squat");
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SupersetThatContainsExercise_ReturnsTrue() {
		ExerciseDescription description1 = new ExerciseDescription("Bench Press");
		Exercise exercise1 = new Exercise(description1);
		
		ExerciseDescription description2 = new ExerciseDescription("Dip");
		Exercise exercise2 = new Exercise(description2);
		
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
		
		Superset set = new Superset(exercises);
		
		ContainsExerciseSpecification specification = new ContainsExerciseSpecification("Bench Press");
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_SupersetThatDoesntContainExercise_ReturnsFalse() {
		ExerciseDescription description1 = new ExerciseDescription("Bench Press");
		Exercise exercise1 = new Exercise(description1);
		
		ExerciseDescription description2 = new ExerciseDescription("Dip");
		Exercise exercise2 = new Exercise(description2);
		
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
		
		Superset set = new Superset(exercises);
		
		ContainsExerciseSpecification specification = new ContainsExerciseSpecification("Squat");
		
		boolean isSatisfied = specification.isSatisfiedBy(set);
		
		assertFalse(isSatisfied);
	}

}
