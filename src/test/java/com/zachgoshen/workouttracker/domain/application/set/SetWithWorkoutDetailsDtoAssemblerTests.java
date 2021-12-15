package com.zachgoshen.workouttracker.domain.application.set;

import static com.zachgoshen.workouttracker.domain.application.set.SetDtoAssertions.assertSetDtoMatchesSet;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.set.SetWithWorkoutDetailsDto;
import com.zachgoshen.workouttracker.application.set.SetWithWorkoutDetailsDtoAssembler;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;
import com.zachgoshen.workouttracker.domain.workout.Workout;

public class SetWithWorkoutDetailsDtoAssemblerTests {
	
	@Test
	public void Assemble_SingleExerciseSetWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException {
		Exercise exercise = buildExercise1();
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.addBoundedRestTimeConstraint(120f, 240f);
		
		Workout workout = new Workout();
		workout.setName("Push Day");
		workout.setTimeCompleted(new Date());
		
		SetWithWorkoutDetailsDto dto = SetWithWorkoutDetailsDtoAssembler.assemble(set, workout);
		
		assertSetDtoMatchesSet(dto, set);
		assertEquals(workout.getId(), dto.getWorkoutId());
		assertEquals(workout.getName().get(), dto.getWorkoutName());
		assertEquals(workout.getTimeCompleted().get(), dto.getWorkoutCompletionTime());
	}
	
	@Test
	public void Assemble_SupersetWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException {
		Exercise exercise1 = buildExercise1();
		Exercise exercise2 = buildExercise2();
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
		
		Superset set = new Superset(exercises);
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.addBoundedRestTimeConstraint(120f, 240f);
		
		Workout workout = new Workout();
		workout.setName("Push Day");
		workout.setTimeCompleted(new Date());

		SetWithWorkoutDetailsDto dto = SetWithWorkoutDetailsDtoAssembler.assemble(set, workout);

		assertSetDtoMatchesSet(dto, set);
		assertEquals(workout.getId(), dto.getWorkoutId());
		assertEquals(workout.getName().get(), dto.getWorkoutName());
		assertEquals(workout.getTimeCompleted().get(), dto.getWorkoutCompletionTime());
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
