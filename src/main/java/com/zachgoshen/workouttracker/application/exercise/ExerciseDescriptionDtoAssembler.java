package com.zachgoshen.workouttracker.application.exercise;

import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

public class ExerciseDescriptionDtoAssembler {
	
	public static ExerciseDescriptionDto assemble(ExerciseDescription description) {
		ExerciseDescriptionDto dto = new ExerciseDescriptionDto();
		
		String id = description.getId();
		dto.setId(id);
		
		String name = description.getName();
		dto.setName(name);
		
		return dto;
	}

}
