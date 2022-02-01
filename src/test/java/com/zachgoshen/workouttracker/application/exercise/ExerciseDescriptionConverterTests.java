package com.zachgoshen.workouttracker.application.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.MuscleGroup;

public class ExerciseDescriptionConverterTests {
	
	@Test
	public void ToDto_ExerciseDescriptionWithAllFieldsSet_DtoHasAllFieldsSet() throws DtoConversionException {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		description.addMuscleGroup(MuscleGroup.PECS);
		description.addMuscleGroup(MuscleGroup.TRICEPS);
		description.addMuscleGroup(MuscleGroup.DELTS);
		
		ExerciseDescriptionDto dto = ExerciseDescriptionConverter.toDto(description);

		assertEquals(description.getId(), dto.getId());
		assertEquals(description.getName(), dto.getName());
		assertEquals(3, dto.getMuscleGroups().size());
		assertTrue(dto.getMuscleGroups().contains("Pecs"));
		assertTrue(dto.getMuscleGroups().contains("Triceps"));
		assertTrue(dto.getMuscleGroups().contains("Delts"));
	}
	
	@Test
	public void ToEntity_DtoWithAllFieldsSet_ExerciseDescriptionHasAllFieldsSet() throws DtoConversionException {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		dto.setName("Bench Press");
		
		List<String> muscleGroups = Arrays.asList("Pecs", "Triceps", "Delts");
		dto.setMuscleGroups(muscleGroups);
		
		ExerciseDescription description = ExerciseDescriptionConverter.toEntity(dto);

		assertEquals(dto.getName(), description.getName());
		assertEquals(3, description.getMuscleGroups().size());
		assertTrue(description.getMuscleGroups().contains(MuscleGroup.PECS));
		assertTrue(description.getMuscleGroups().contains(MuscleGroup.TRICEPS));
		assertTrue(description.getMuscleGroups().contains(MuscleGroup.DELTS));
	}

}
