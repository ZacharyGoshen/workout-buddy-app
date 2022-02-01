package com.zachgoshen.workoutbuddy.application.exercise;

import java.util.ArrayList;
import java.util.List;

import com.zachgoshen.workoutbuddy.application.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.MuscleGroup;

public class ExerciseDescriptionConverter {
	
	public static ExerciseDescriptionDto toDto(ExerciseDescription description) {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		
		String id = description.getId();
		dto.setId(id);
		
		String name = description.getName();
		dto.setName(name);
		
		List<MuscleGroup> muscleGroups = description.getMuscleGroups();
		List<String> muscleGroupStrings = buildMuscleGroupStrings(muscleGroups);
		dto.setMuscleGroups(muscleGroupStrings);
		
		return dto;
	}
	
	private static List<String> buildMuscleGroupStrings(List<MuscleGroup> muscleGroups) {
		List<String> muscleGroupStrings = new ArrayList<>();
		
		for (MuscleGroup muscleGroup : muscleGroups) {
			String muscleGroupString = MuscleGroupConverter.toString(muscleGroup);
			muscleGroupStrings.add(muscleGroupString);
		}
		
		return muscleGroupStrings;
	}
	
	public static ExerciseDescription toEntity(ExerciseDescriptionDto dto) throws DtoConversionException {
		String name = dto.getName();
		
		if (name == null) {
			throw new DtoConversionException("Name can't be null");
		}
		
		ExerciseDescription exerciseDescription = new ExerciseDescription(name);
		
		List<String> muscleGroupStrings = dto.getMuscleGroups();
		
		for (String muscleGroupString : muscleGroupStrings) {
			MuscleGroup muscleGroup = MuscleGroupConverter.toEnum(muscleGroupString);
			exerciseDescription.addMuscleGroup(muscleGroup);
		}
		
		return exerciseDescription;
	}

}
