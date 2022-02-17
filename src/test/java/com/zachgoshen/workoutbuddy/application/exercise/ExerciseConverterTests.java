package com.zachgoshen.workoutbuddy.application.exercise;

import static com.zachgoshen.workoutbuddy.application.exercise.ExerciseDtoAssertions.assertExerciseDtoMatchesExercise;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseConverter;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDto;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;

public class ExerciseConverterTests {
	
	@Test
	public void ToDto_ExerciseWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(225f);
		exercise.addBoundedWeightConstraint(220f, 230f);
		exercise.setRepsCompleted(5);
		exercise.addBoundedRepsConstraint(4, 6);
		exercise.setTimePerformed(30f);
		exercise.addBoundedDurationConstraint(15f, 45f);
		
		ExerciseDto dto = ExerciseConverter.toDto(exercise);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}
	
	@Test
	public void ToDto_DtoWithAllFieldsSet_ExerciseHasAllFieldsSet() throws InvalidRangeException, DtoConversionException {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName("Bench Press");
		dto.setWeightUsed(225f);
		dto.setMinimumWeightAllowed(220f);
		dto.setMaximumWeightAllowed(230f);
		dto.setRepsCompleted(5);
		dto.setMinimumRepsAllowed(4);
		dto.setMaximumRepsAllowed(6);
		dto.setTimePerformed(30f);
		dto.setMinimumDurationAllowed(15f);
		dto.setMaximumDurationAllowed(45f);
		
		Exercise exercise = ExerciseConverter.toEntity(dto);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}

}
