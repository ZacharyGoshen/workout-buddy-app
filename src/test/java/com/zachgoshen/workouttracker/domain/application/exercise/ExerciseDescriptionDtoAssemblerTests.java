package com.zachgoshen.workouttracker.domain.application.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workouttracker.application.exercise.ExerciseDescriptionDtoAssembler;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

public class ExerciseDescriptionDtoAssemblerTests {
	
	@Test
	public void Assemble_ExerciseDescriptionWithAllFieldsSet_DtoHasAllFieldsSet() {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		ExerciseDescriptionDto dto = ExerciseDescriptionDtoAssembler.assemble(description);
		
		assertEquals(description.getId(), dto.getId());
		assertEquals(description.getName(), dto.getName());
	}

}
