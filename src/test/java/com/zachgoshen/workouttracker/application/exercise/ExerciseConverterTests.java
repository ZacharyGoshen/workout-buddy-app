package com.zachgoshen.workouttracker.application.exercise;

import static com.zachgoshen.workouttracker.application.exercise.ExerciseDtoAssertions.assertExerciseDtoMatchesExercise;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.exercise.ExerciseDto;
import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.exercise.ExerciseConverter;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;

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
