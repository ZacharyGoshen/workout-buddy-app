package com.zachgoshen.workoutbuddy.api.exercise;

import static com.zachgoshen.workoutbuddy.api.exercise.ExerciseDtoAssertions.assertExerciseDtoMatchesExercise;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;

public class ExerciseConverterTests {
	
	@Test
	public void ToDto_AllOptionalFieldsSet() throws InvalidRangeException {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(225f);
		exercise.addBoundedWeightConstraint(220f, 230f);
		exercise.setRepsCompleted(5);
		exercise.addBoundedRepsConstraint(4, 6);
		exercise.setTimePerformed(30f);
		exercise.addBoundedDurationConstraint(15f, 45f);
		
		ExerciseDto dto = ExerciseConverter.toDto(exercise);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToDto_NoOptionalFieldsSet() throws InvalidRangeException {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		Exercise exercise = new Exercise(description);
		
		ExerciseDto dto = ExerciseConverter.toDto(exercise);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToEntity_DtoWithBoundedWeightConstraint() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setWeightUsed(225f);
		dto.setMinimumWeightAllowed(220f);
		dto.setMaximumWeightAllowed(230f);
		
		Exercise exercise = ExerciseConverter.toEntity(dto);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToEntity_DtoWithMinimumWeightConstraint() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setWeightUsed(225f);
		dto.setMinimumWeightAllowed(220f);
		
		Exercise exercise = ExerciseConverter.toEntity(dto);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToEntity_DtoWithMaximumWeightConstraint_ThrowsDtoConversionException() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setWeightUsed(225f);
		dto.setMaximumWeightAllowed(230f);
		
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> ExerciseConverter.toEntity(dto));
		
		assertEquals("Invalid weight constraint", exception.getMessage());
	}
	
	@Test
	public void ToEntity_DtoWithBoundedRepsConstraint() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setRepsCompleted(5);
		dto.setMinimumRepsAllowed(4);
		dto.setMaximumRepsAllowed(6);
		
		Exercise exercise = ExerciseConverter.toEntity(dto);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToEntity_DtoWithMinimumRepsConstraint() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setRepsCompleted(5);
		dto.setMinimumRepsAllowed(4);
		
		Exercise exercise = ExerciseConverter.toEntity(dto);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToEntity_DtoWithMaximumRepsConstraint_ThrowsDtoConversionException() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setRepsCompleted(5);
		dto.setMaximumRepsAllowed(6);
		
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> ExerciseConverter.toEntity(dto));
		
		assertEquals("Invalid reps constraint", exception.getMessage());
	}
	
	@Test
	public void ToEntity_DtoWithBoundedDurationConstraint() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setTimePerformed(30f);
		dto.setMinimumDurationAllowed(15f);
		dto.setMaximumDurationAllowed(45f);
		
		Exercise exercise = ExerciseConverter.toEntity(dto);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToEntity_DtoWithMinimumDurationConstraint() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setTimePerformed(30f);
		dto.setMinimumDurationAllowed(15f);
		
		Exercise exercise = ExerciseConverter.toEntity(dto);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToEntity_DtoWithMaximumDurationConstraint_ThrowsDtoConversionException() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setTimePerformed(30f);
		dto.setMaximumDurationAllowed(45f);
		
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> ExerciseConverter.toEntity(dto));
		
		assertEquals("Invalid duration constraint", exception.getMessage());
	}
	
	@Test
	public void ToEntity_DtoWithNoConstraints() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		dto.setName("Bench Press");
		
		Exercise exercise = ExerciseConverter.toEntity(dto);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}

}
