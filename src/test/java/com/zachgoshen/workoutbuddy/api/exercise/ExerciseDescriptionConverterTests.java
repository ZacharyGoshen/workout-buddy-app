package com.zachgoshen.workoutbuddy.api.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public class ExerciseDescriptionConverterTests {
	
	@Test
	public void ToDto_ExerciseDescriptionWithAllFieldsSet_DtoHasAllFieldsSet() throws DtoConversionException {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		description.setNotes("Arch back and keep feet planted.");
		description.addMuscleGroup(MuscleGroup.PECS);
		description.addMuscleGroup(MuscleGroup.TRICEPS);
		description.addMuscleGroup(MuscleGroup.DELTS);
		
		ExerciseDescriptionDto dto = ExerciseDescriptionConverter.toDto(description);

		assertEquals(description.getId(), dto.getId());
		assertEquals(description.getName(), dto.getName());
		assertEquals(description.getNotes(), dto.getNotes());
		assertEquals(3, dto.getMuscleGroups().size());
		assertTrue(dto.getMuscleGroups().contains("Pecs"));
		assertTrue(dto.getMuscleGroups().contains("Triceps"));
		assertTrue(dto.getMuscleGroups().contains("Delts"));
	}
	
	@Test
	public void ToEntity_DtoWithAllFieldsSet_ExerciseDescriptionHasAllFieldsSet() throws DtoConversionException {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		dto.setName("Bench Press");
		dto.setNotes("Arch back and keep feet planted.");
		
		List<String> muscleGroups = Arrays.asList("Pecs", "Triceps", "Delts");
		dto.setMuscleGroups(muscleGroups);
		
		ExerciseDescription description = ExerciseDescriptionConverter.toEntity(dto);

		assertEquals(dto.getName(), description.getName());
		assertEquals(dto.getNotes(), description.getNotes());
		assertEquals(3, description.getMuscleGroups().size());
		assertTrue(description.getMuscleGroups().contains(MuscleGroup.PECS));
		assertTrue(description.getMuscleGroups().contains(MuscleGroup.TRICEPS));
		assertTrue(description.getMuscleGroups().contains(MuscleGroup.DELTS));
	}
	
	@Test
	public void ToEntity_NameIsNull_ThrowsDtoConversionException() throws DtoConversionException {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> ExerciseDescriptionConverter.toEntity(dto));
		
		assertEquals("Name can't be null", exception.getMessage());
	}

}
