package com.zachgoshen.workoutbuddy.api.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public class ExerciseDescriptionDtoAssertions {
	
	public static void assertExerciseDescriptionDtoMatchesExerciseDescription(ExerciseDescriptionDto dto, ExerciseDescription description) {
		assertEquals(description.getName(), dto.getName());
		assertEquals(description.getNotes(), dto.getNotes());
		assertMuscleGroupsAsStringsMatchesMuscleGroups(dto.getMuscleGroups(), description.getMuscleGroups());
	}
	
	public static void assertExerciseDescriptionUpdateDtoWasAppliedToExerciseDescription(ExerciseDescriptionDto updateDto, ExerciseDescription description) {
		String updateDtoName = updateDto.getName();
		if (updateDtoName != null) {
			assertEquals(updateDtoName, description.getName());
		}
		
		String updateDtoNotes = updateDto.getNotes();
		if (updateDtoNotes != null) {
			assertEquals(updateDtoNotes, description.getNotes());
		}
		
		List<String> updateDtoMuscleGroups = updateDto.getMuscleGroups();
		if (updateDtoMuscleGroups != null) {
			assertMuscleGroupsAsStringsMatchesMuscleGroups(updateDto.getMuscleGroups(), description.getMuscleGroups());
		}
	}
	
	private static void assertMuscleGroupsAsStringsMatchesMuscleGroups(List<String> muscleGroupsAsStrings, List<MuscleGroup> muscleGroups) {
		assertEquals(muscleGroups.size(), muscleGroupsAsStrings.size());
		
		for (MuscleGroup muscleGroup: muscleGroups) {
			String muscleGroupAsString = MuscleGroupConverter.toString(muscleGroup);
			assertTrue(muscleGroupsAsStrings.contains(muscleGroupAsString));
		}
	}

}
