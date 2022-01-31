package com.zachgoshen.workouttracker.application.set;

import static com.zachgoshen.workouttracker.application.set.SetDtoAssertions.assertSetDtoMatchesSet;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.set.SetDto;
import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.exercise.ExerciseDto;
import com.zachgoshen.workouttracker.application.set.SetConverter;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;

public class SetConverterTests {
	
	@Test
	public void ToDto_SingleExerciseSetWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException {
		Exercise exercise = buildExercise1();
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.addBoundedRestTimeConstraint(120f, 240f);
		
		SetDto dto = SetConverter.toDto(set);
		
		assertSetDtoMatchesSet(dto, set);
	}
	
	@Test
	public void ToDto_SupersetWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException {
		Exercise exercise1 = buildExercise1();
		Exercise exercise2 = buildExercise2();
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
		
		Superset set = new Superset(exercises);
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.addBoundedRestTimeConstraint(120f, 240f);
		
		SetDto dto = SetConverter.toDto(set);

		assertSetDtoMatchesSet(dto, set);
	}
	
	@Test
	public void ToEntity_SingleExerciseSetWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException, DtoConversionException {
		ExerciseDto exerciseDto = buildExerciseDto1();
		
		SetDto setDto = new SetDto();
		setDto.setType("Single Exercise Set");
		setDto.setTimeCompleted(new Date());
		setDto.setTimeRested(180f);
		setDto.setMinimumRestTimeAllowed(120f);
		setDto.setMaximumRestTimeAllowed(240f);
		setDto.setExercises(Arrays.asList(exerciseDto));
		
		Set set = SetConverter.toEntity(setDto);
		
		assertSetDtoMatchesSet(setDto, set);
	}
	
	@Test
	public void ToEntity_SupersetWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException, DtoConversionException {
		ExerciseDto exerciseDto1 = buildExerciseDto1();
		ExerciseDto exerciseDto2 = buildExerciseDto2();
		
		SetDto setDto = new SetDto();
		setDto.setType("Superset");
		setDto.setTimeCompleted(new Date());
		setDto.setTimeRested(180f);
		setDto.setMinimumRestTimeAllowed(120f);
		setDto.setMaximumRestTimeAllowed(240f);
		setDto.setExercises(Arrays.asList(exerciseDto1, exerciseDto2));
		
		Set set = SetConverter.toEntity(setDto);
		
		assertSetDtoMatchesSet(setDto, set);
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
	
	private static ExerciseDto buildExerciseDto1() throws InvalidRangeException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setWeightUsed(225f);
		dto.setMinimumWeightAllowed(220f);
		dto.setMaximumWeightAllowed(230f);
		dto.setRepsCompleted(5);
		dto.setMinimumRepsAllowed(4);
		dto.setMaximumRepsAllowed(6);
		
		return dto;
	}
	
	private static ExerciseDto buildExerciseDto2() throws InvalidRangeException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Pushup");
		dto.setRepsCompleted(20);
		dto.setMinimumRepsAllowed(15);
		dto.setMaximumRepsAllowed(25);
		dto.setTimePerformed(60f);
		dto.setMinimumDurationAllowed(30f);
		dto.setMaximumDurationAllowed(90f);
		
		return dto;
	}

}
