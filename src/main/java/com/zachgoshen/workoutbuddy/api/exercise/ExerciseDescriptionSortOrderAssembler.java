package com.zachgoshen.workoutbuddy.api.exercise;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionSortOrder;

public final class ExerciseDescriptionSortOrderAssembler {
	
	private ExerciseDescriptionSortOrderAssembler() {}
	
	public static ExerciseDescriptionSortOrder assemble(String sortOrder) throws DtoConversionException {
		switch (sortOrder) {
			case "nameAlphabetically":
				return ExerciseDescriptionSortOrder.NAME_ALPHABETICALLY;
			case "nameReverseAlphabetically":
				return ExerciseDescriptionSortOrder.NAME_REVERSE_ALPHABETICALLY;
			default:
				String message = String.format("'%s' is not a valid sorting order for exercise descriptions", sortOrder);
				throw new DtoConversionException(message);
		}
	}

}
