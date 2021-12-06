package com.zachgoshen.workouttracker.domain.application.workout.exercise;

import static com.zachgoshen.workouttracker.domain.application.workout.exercise.ExerciseDtoAssertions.assertExerciseDtoMatchesExercise;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.application.workout.exercise.ExerciseDto;
import com.zachgoshen.workouttracker.application.workout.exercise.ExerciseDtoAssembler;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;

public class ExerciseDtoAssemblerTests {
	
	@Test
	public void Assemble_ExerciseWithAllFieldsSet_DtoHasAllFieldsSet() throws InvalidRangeException {
		ExerciseDescription description = new ExerciseDescription("Bench Press");
		
		Exercise exercise = new Exercise(description);
		exercise.setWeightUsed(225f);
		exercise.addBoundedWeightConstraint(220f, 230f);
		exercise.setRepsCompleted(5);
		exercise.addBoundedRepsConstraint(4, 6);
		exercise.setTimePerformed(30f);
		exercise.addBoundedDurationConstraint(15f, 45f);
		
		ExerciseDto dto = ExerciseDtoAssembler.assemble(exercise);
		
		assertExerciseDtoMatchesExercise(dto, exercise);
	}

}