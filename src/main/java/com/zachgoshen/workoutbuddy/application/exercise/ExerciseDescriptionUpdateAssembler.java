package com.zachgoshen.workoutbuddy.application.exercise;

import java.util.ArrayList;
import java.util.List;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workoutbuddy.api.exercise.MuscleGroupConverter;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public class ExerciseDescriptionUpdateAssembler {
	
	public static ExerciseDescriptionUpdate assemble(ExerciseDescriptionDto dto) throws DtoConversionException {
		ExerciseDescriptionUpdate update = new ExerciseDescriptionUpdate();
		
		String name = dto.getName();
		update.setName(name);
		
		List<MuscleGroup> muscleGroups = buildMuscleGroups(dto);
		update.setMuscleGroups(muscleGroups);
		
		return update;
	}
	
	private static List<MuscleGroup> buildMuscleGroups(ExerciseDescriptionDto dto) throws DtoConversionException {
		List<String> muscleGroupStrings = dto.getMuscleGroups();
		List<MuscleGroup> muscleGroups = new ArrayList<>();
		
		for (String muscleGroupString: muscleGroupStrings) {
			MuscleGroup muscleGroup = MuscleGroupConverter.toEnum(muscleGroupString);
			muscleGroups.add(muscleGroup);
		}
		
		return muscleGroups;
	}

}
