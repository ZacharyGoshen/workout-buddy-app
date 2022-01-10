package com.zachgoshen.workouttracker.domain.application.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.UUID;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.exercise.ExerciseDescriptionConverter;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

public class ExerciseDescriptionConverterTests {
	
	@Test
	public void ToDto_ExerciseDescriptionWithAllFieldsSet_DtoHasAllFieldsSet() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		ExerciseDescriptionDto dto = ExerciseDescriptionConverter.toDto(description);

		assertEquals(description.getId(), dto.getId());
		assertEquals(description.getName(), dto.getName());
	}
	
	@Test
	public void ToEntity_DtoWithAllFieldsSet_ExerciseDescriptionHasAllFieldsSet() throws DtoConversionException {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		dto.setId(UUID.randomUUID().toString());
		dto.setName("Bench Press");
		
		ExerciseDescription description = ExerciseDescriptionConverter.toEntity(dto);

		assertEquals(dto.getId(), description.getId());
		assertEquals(dto.getName(), description.getName());
	}

}
