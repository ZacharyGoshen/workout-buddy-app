package com.zachgoshen.workoutbuddy.api.exercise;

import java.util.ArrayList;
import java.util.List;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionUpdate;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public final class ExerciseDescriptionUpdateAssembler {
	
	private ExerciseDescriptionUpdateAssembler() {}
	
	public static ExerciseDescriptionUpdate assemble(ExerciseDescriptionDto dto) throws DtoConversionException {
		ExerciseDescriptionUpdate update = new ExerciseDescriptionUpdate();

		String name = dto.getName();
		
		if (name == null) {
			throw new DtoConversionException("Name can't be null");
		}
		
		update.setName(name);
		
		String notes = dto.getNotes();
		update.setNotes(notes);
		
		List<MuscleGroup> muscleGroups = buildMuscleGroups(dto);
		update.setMuscleGroups(muscleGroups);
		
		return update;
	}
	
	private static List<MuscleGroup> buildMuscleGroups(ExerciseDescriptionDto dto) throws DtoConversionException {
		List<String> muscleGroupStrings = dto.getMuscleGroups();
		
		if (muscleGroupStrings == null) {
			return null;
		}
		
		List<MuscleGroup> muscleGroups = new ArrayList<>();
		
		for (String muscleGroupString: muscleGroupStrings) {
			MuscleGroup muscleGroup = MuscleGroupConverter.toEnum(muscleGroupString);
			muscleGroups.add(muscleGroup);
		}
		
		return muscleGroups;
	}

}
