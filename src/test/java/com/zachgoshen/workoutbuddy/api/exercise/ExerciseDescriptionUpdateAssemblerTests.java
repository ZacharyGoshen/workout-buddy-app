package com.zachgoshen.workoutbuddy.api.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionUpdate;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public class ExerciseDescriptionUpdateAssemblerTests {
	
	@Test
	public void Assemble_NonNullValues() throws DtoConversionException {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		dto.setName("Squat");
		dto.setNotes("Squat Notes");
		
		List<String> muscleGroups = Arrays.asList("Glutes", "Hamstrings", "Lower Back", "Quads");
		dto.setMuscleGroups(muscleGroups);
		
		ExerciseDescriptionUpdate update = ExerciseDescriptionUpdateAssembler.assemble(dto);
		
		assertEquals("Squat", update.getName().get());
		assertEquals("Squat Notes", update.getNotes().get());
		assertTrue(update.getMuscleGroups().get().contains(MuscleGroup.GLUTES));
		assertTrue(update.getMuscleGroups().get().contains(MuscleGroup.HAMSTRINGS));
		assertTrue(update.getMuscleGroups().get().contains(MuscleGroup.LOWER_BACK));
		assertTrue(update.getMuscleGroups().get().contains(MuscleGroup.QUADS));
	}
	
	@Test
	public void Assemble_NullValuesExceptName() throws DtoConversionException {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		dto.setName("Squat");
		dto.setNotes(null);
		dto.setMuscleGroups(null);
		
		ExerciseDescriptionUpdate update = ExerciseDescriptionUpdateAssembler.assemble(dto);

		assertEquals("Squat", update.getName().get());
		assertFalse(update.getMuscleGroups().isPresent());
	}
	
	@Test
	public void Assemble_NullName_ThrowsDtoConversionException() throws DtoConversionException {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		dto.setName(null);
		dto.setNotes("Squat Notes");
		
		List<String> muscleGroups = Arrays.asList("Glutes", "Hamstrings", "Lower Back", "Quads");
		dto.setMuscleGroups(muscleGroups);
		
		assertThrows(DtoConversionException.class, () -> ExerciseDescriptionUpdateAssembler.assemble(dto));
	}
	
	@Test
	public void Assemble_InvalidMuscleGroup_ThrowsDtoConversionException() throws DtoConversionException {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		dto.setName("Squat");
		dto.setNotes("Squat Notes");
		
		List<String> muscleGroups = Arrays.asList("Invalid Muscle Group");
		dto.setMuscleGroups(muscleGroups);
		
		assertThrows(DtoConversionException.class, () -> ExerciseDescriptionUpdateAssembler.assemble(dto));
	}

}
