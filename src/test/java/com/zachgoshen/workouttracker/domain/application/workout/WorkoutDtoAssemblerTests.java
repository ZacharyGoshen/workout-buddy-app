package com.zachgoshen.workouttracker.domain.application.workout;

import static com.zachgoshen.workouttracker.domain.application.workout.WorkoutDtoAssertions.assertWorkoutDtoMatchesWorkout;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.workout.WorkoutDto;
import com.zachgoshen.workouttracker.application.workout.WorkoutDtoAssembler;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.workout.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.workout.set.Superset;

public class WorkoutDtoAssemblerTests {
	
	@Test
	public void Assemble_WorkoutWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException {
		Workout workout = new Workout();
		workout.setName("Push Day");
		workout.setTimeCompleted(new Date());
		workout.appendSet(buildSingleExerciseSet());
		workout.appendSet(buildSuperset());
		
		WorkoutDto dto = WorkoutDtoAssembler.assemble(workout);
		
		assertWorkoutDtoMatchesWorkout(dto, workout);
	}
	
	private static SingleExerciseSet buildSingleExerciseSet() throws InvalidRangeException {
		Exercise exercise = buildExercise1();
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.addBoundedRestTimeConstraint(120f, 240f);
		
		return set;
	}
	
	private static Superset buildSuperset() throws InvalidRangeException {
		Exercise exercise1 = buildExercise1();
		Exercise exercise2 = buildExercise2();
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
		
		Superset set = new Superset(exercises);
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.addBoundedRestTimeConstraint(120f, 240f);
		
		return set;
	}
	
	private static Exercise buildExercise1() throws InvalidRangeException {
		ExerciseDescription description1 = new ExerciseDescription("Bench Press");
		
		Exercise exercise1 = new Exercise(description1);
		exercise1.setWeightUsed(225f);
		exercise1.addBoundedWeightConstraint(220f, 230f);
		exercise1.setRepsCompleted(5);
		exercise1.addBoundedRepsConstraint(4, 6);
		
		return exercise1;
	}
	
	private static Exercise buildExercise2() throws InvalidRangeException {
		ExerciseDescription description2 = new ExerciseDescription("Pushup");
		
		Exercise exercise2 = new Exercise(description2);
		exercise2.setRepsCompleted(20);
		exercise2.addBoundedRepsConstraint(15, 25);
		exercise2.setTimePerformed(60f);
		exercise2.addBoundedDurationConstraint(30f, 90f);
		
		return exercise2;
	}

}
