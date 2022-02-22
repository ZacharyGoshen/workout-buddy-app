package com.zachgoshen.workoutbuddy.api.set;

import static com.zachgoshen.workoutbuddy.api.set.SetDtoAssertions.assertSetDtoMatchesSet;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDto;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.Superset;

public class SetConverterTests {
	
	@Test
	public void ToDto_SingleExerciseSetWithAllOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
		Exercise exercise = buildExercise1();
		
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		set.setTimeCompleted(new Date());
		set.setTimeRested(180f);
		set.addBoundedRestTimeConstraint(120f, 240f);
		
		SetDto dto = SetConverter.toDto(set);
		
		assertSetDtoMatchesSet(dto, set);
	}
	
	@Test
	public void ToDto_SingleExerciseSetWithNoOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
		Exercise exercise = buildExercise1();
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		SetDto dto = SetConverter.toDto(set);
		
		assertSetDtoMatchesSet(dto, set);
	}
	
	@Test
	public void ToDto_SupersetWithAllOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
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
	public void ToDto_SupersetWithNoOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
		Exercise exercise1 = buildExercise1();
		Exercise exercise2 = buildExercise2();
		List<Exercise> exercises = Arrays.asList(exercise1, exercise2);
		
		Superset set = new Superset(exercises);
		
		SetDto dto = SetConverter.toDto(set);

		assertSetDtoMatchesSet(dto, set);
	}
	
	@Test
	public void ToDto_InvalidSetType_ThrowsDtoConversionException() throws InvalidRangeException, DtoConversionException {
		Set set = buildInvalidSet();
		
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> SetConverter.toDto(set));

		assertEquals("Set can't be converted", exception.getMessage());
	}
	
	@Test
	public void ToEntity_SingleExerciseSetWithAllOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
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
	public void ToEntity_SingleExerciseSetWithNoOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
		ExerciseDto exerciseDto = buildExerciseDto1();
		
		SetDto setDto = new SetDto();
		setDto.setType("Single Exercise Set");
		setDto.setExercises(Arrays.asList(exerciseDto));
		
		Set set = SetConverter.toEntity(setDto);
		
		assertSetDtoMatchesSet(setDto, set);
	}
	
	@Test
	public void ToEntity_SupersetWithAllOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
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
	
	@Test
	public void ToEntity_SupersetWithNoOptionalFieldsSet() throws InvalidRangeException, DtoConversionException {
		ExerciseDto exerciseDto1 = buildExerciseDto1();
		ExerciseDto exerciseDto2 = buildExerciseDto2();
		
		SetDto setDto = new SetDto();
		setDto.setType("Superset");
		setDto.setExercises(Arrays.asList(exerciseDto1, exerciseDto2));
		
		Set set = SetConverter.toEntity(setDto);
		
		assertSetDtoMatchesSet(setDto, set);
	}
	
	@Test
	public void ToEntity_InvalidSetType_ThrowsDtoConversionException() throws InvalidRangeException, DtoConversionException {
		SetDto setDto = new SetDto();
		setDto.setType("Invalid Set Type");
		
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> SetConverter.toEntity(setDto));

		assertEquals("'Invalid Set Type' is not a valid set type", exception.getMessage());
	}
	
	@Test
	public void ToEntity_SetWithMaximumRestTimeConstraint() throws InvalidRangeException, DtoConversionException {
		ExerciseDto exerciseDto = buildExerciseDto1();
		
		SetDto setDto = new SetDto();
		setDto.setType("Single Exercise Set");
		setDto.setMaximumRestTimeAllowed(60f);
		setDto.setExercises(Arrays.asList(exerciseDto));
		
		Set set = SetConverter.toEntity(setDto);
		
		assertSetDtoMatchesSet(setDto, set);
	}
	
	@Test
	public void ToEntity_SetWithMinimumRestTimeConstraint_ThrowsDtoConversionException() throws InvalidRangeException, DtoConversionException {
		ExerciseDto exerciseDto = buildExerciseDto1();
		
		SetDto setDto = new SetDto();
		setDto.setType("Single Exercise Set");
		setDto.setMinimumRestTimeAllowed(60f);
		setDto.setExercises(Arrays.asList(exerciseDto));
		
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> SetConverter.toEntity(setDto));

		assertEquals("Invalid rest time constraint", exception.getMessage());
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
	
	private static Set buildInvalidSet() {
		return new Set() {
			
			@Override
			public boolean wereConstraintsSatisfied() {
				return false;
			}
			
			@Override
			public Set clone() {
				return null;
			}
		};
	}

}
