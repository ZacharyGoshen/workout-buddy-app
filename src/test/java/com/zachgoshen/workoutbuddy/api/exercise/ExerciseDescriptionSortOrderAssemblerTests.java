package com.zachgoshen.workoutbuddy.api.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionSortOrder;

public class ExerciseDescriptionSortOrderAssemblerTests {
	
	@Test
	public void Assemble_NameAlphabetically() throws DtoConversionException {
		ExerciseDescriptionSortOrder sortOrder = ExerciseDescriptionSortOrderAssembler.assemble("nameAlphabetically");
		
		assertEquals(ExerciseDescriptionSortOrder.NAME_ALPHABETICALLY, sortOrder);
	}
	
	@Test
	public void Assemble_NameReverseAlphabetically() throws DtoConversionException {
		ExerciseDescriptionSortOrder sortOrder = ExerciseDescriptionSortOrderAssembler.assemble("nameReverseAlphabetically");
		
		assertEquals(ExerciseDescriptionSortOrder.NAME_REVERSE_ALPHABETICALLY, sortOrder);
	}
	
	@Test
	public void Assemble_InvalidSortOrder_ThrowsDtoConversionException() throws DtoConversionException {
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> ExerciseDescriptionSortOrderAssembler.assemble("invalidSortOrder"));
		
		assertEquals("'invalidSortOrder' is not a valid sorting order for exercise descriptions", exception.getMessage());
	}

}
