package com.zachgoshen.workouttracker.application.exercise;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

public class ExerciseDescriptionConverter {
	
	public static ExerciseDescriptionDto toDto(ExerciseDescription description) {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		
		String id = description.getId();
		dto.setId(id);
		
		String name = description.getName();
		dto.setName(name);
		
		return dto;
	}
	
	public static ExerciseDescription toEntity(ExerciseDescriptionDto dto) throws DtoConversionException {
		String name = dto.getName();
		
		if (name == null) {
			throw new DtoConversionException("Name can't be null");
		}
		
		return new ExerciseDescription(name);
	}

}
