package com.zachgoshen.workoutbuddy.api.workout;

import static com.zachgoshen.workoutbuddy.api.workout.WorkoutDtoAssertions.assertWorkoutDtoMatchesWorkout;
import static com.zachgoshen.workoutbuddy.api.workout.WorkoutDtoAssertions.assertWorkoutDtoMatchesWorkoutWithoutComparingIds;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDto;
import com.zachgoshen.workoutbuddy.api.set.SetDto;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.Superset;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class WorkoutConverterTests {
	
	@Test
	public void ToDto_WorkoutWithAllOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
		Workout workout = new Workout();
		workout.setName("Push Day");
		workout.setTimeCompleted(new Date());
		workout.appendSet(buildSingleExerciseSet());
		workout.appendSet(buildSuperset());
		
		WorkoutDto dto = WorkoutConverter.toDto(workout);
		
		assertWorkoutDtoMatchesWorkout(dto, workout);
	}
	
	@Test
	public void ToDto_WorkoutWithNoOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
		Workout workout = new Workout();
		
		WorkoutDto dto = WorkoutConverter.toDto(workout);
		
		assertWorkoutDtoMatchesWorkout(dto, workout);
	}
	
	@Test
	public void ToEntity_DtoWithAllFieldsSet() throws InvalidRangeException, DtoConversionException {
		WorkoutDto workoutDto = new WorkoutDto();
		workoutDto.setName("Push Day");
		workoutDto.setTimeCompleted(new Date());
		
		SetDto singleExerciseSetDto = buildSingleExerciseSetDto();
		SetDto supersetDto = buildSupersetDto();
		List<SetDto> setDtos = Arrays.asList(singleExerciseSetDto, supersetDto);
		workoutDto.setSets(setDtos);
		
		Workout workout = WorkoutConverter.toEntity(workoutDto);
		
		assertWorkoutDtoMatchesWorkoutWithoutComparingIds(workoutDto, workout);
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
	
	private static SetDto buildSingleExerciseSetDto() throws InvalidRangeException {
		SetDto set = new SetDto();

		set.setType("Single Exercise Set");
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.setMinimumRestTimeAllowed(120f);
		set.setMaximumRestTimeAllowed(240f);
		
		ExerciseDto exercise = buildExerciseDto1();
		List<ExerciseDto> exercises = Arrays.asList(exercise);
		set.setExercises(exercises);
		
		return set;
	}
	
	private static SetDto buildSupersetDto() throws InvalidRangeException {
		SetDto set = new SetDto();
		
		set.setType("Superset");
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.setMinimumRestTimeAllowed(120f);
		set.setMaximumRestTimeAllowed(240f);
		
		ExerciseDto exercise1 = buildExerciseDto1();
		ExerciseDto exercise2 = buildExerciseDto2();
		List<ExerciseDto> exercises = Arrays.asList(exercise1, exercise2);
		set.setExercises(exercises);
		
		return set;
	}
	
	private static ExerciseDto buildExerciseDto1() throws InvalidRangeException {
		ExerciseDto exercise = new ExerciseDto();
		
		exercise.setName("Bench Press");
		exercise.setWeightUsed(225f);
		exercise.setMinimumWeightAllowed(220f);
		exercise.setMaximumWeightAllowed(230f);
		exercise.setRepsCompleted(5);
		exercise.setMinimumRepsAllowed(4);
		exercise.setMaximumRepsAllowed(6);
		
		return exercise;
	}
	
	private static ExerciseDto buildExerciseDto2() throws InvalidRangeException {
		ExerciseDto exercise = new ExerciseDto();
		
		exercise.setName("Pushup");
		exercise.setRepsCompleted(20);
		exercise.setMinimumRepsAllowed(15);
		exercise.setMaximumRepsAllowed(25);
		exercise.setTimePerformed(60f);
		exercise.setMinimumDurationAllowed(30f);
		exercise.setMaximumDurationAllowed(90f);
		
		return exercise;
	}

}
