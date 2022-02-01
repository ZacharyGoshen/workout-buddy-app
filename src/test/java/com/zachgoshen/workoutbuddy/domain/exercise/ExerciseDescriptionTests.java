package com.zachgoshen.workoutbuddy.domain.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.MuscleGroup;

public class ExerciseDescriptionTests {
	
	@Test
	public void AddMuscleGroup_UniqueMuscleGroups_AllMuscleGroupsAreAdded() {
		ExerciseDescription exercise = new ExerciseDescription("Bench Press");
		exercise.addMuscleGroup(MuscleGroup.PECS);
		exercise.addMuscleGroup(MuscleGroup.TRICEPS);
		exercise.addMuscleGroup(MuscleGroup.DELTS);
		
		List<MuscleGroup> muscleGroups = exercise.getMuscleGroups();
		
		assertEquals(3, muscleGroups.size());
		assertTrue(muscleGroups.contains(MuscleGroup.PECS));
		assertTrue(muscleGroups.contains(MuscleGroup.TRICEPS));
		assertTrue(muscleGroups.contains(MuscleGroup.DELTS));
	}
	
	@Test
	public void AddMuscleGroup_DuplicateMuscleGroup_DuplicateMuscleGroupsAreNotAdded() {
		ExerciseDescription exercise = new ExerciseDescription("Bench Press");
		exercise.addMuscleGroup(MuscleGroup.PECS);
		exercise.addMuscleGroup(MuscleGroup.TRICEPS);
		exercise.addMuscleGroup(MuscleGroup.DELTS);
		exercise.addMuscleGroup(MuscleGroup.PECS);
		exercise.addMuscleGroup(MuscleGroup.TRICEPS);
		exercise.addMuscleGroup(MuscleGroup.DELTS);
		
		List<MuscleGroup> muscleGroups = exercise.getMuscleGroups();
		
		assertEquals(3, muscleGroups.size());
		assertTrue(muscleGroups.contains(MuscleGroup.PECS));
		assertTrue(muscleGroups.contains(MuscleGroup.TRICEPS));
		assertTrue(muscleGroups.contains(MuscleGroup.DELTS));
	}
	
	@Test
	public void RemoveMuscleGroup() {
		ExerciseDescription exercise = new ExerciseDescription("Bench Press");
		exercise.addMuscleGroup(MuscleGroup.PECS);
		exercise.addMuscleGroup(MuscleGroup.TRICEPS);
		exercise.addMuscleGroup(MuscleGroup.DELTS);
		exercise.addMuscleGroup(MuscleGroup.QUADS);
		
		exercise.removeMuscleGroup(MuscleGroup.QUADS);
		
		List<MuscleGroup> muscleGroups = exercise.getMuscleGroups();
		
		assertEquals(3, muscleGroups.size());
		assertTrue(muscleGroups.contains(MuscleGroup.PECS));
		assertTrue(muscleGroups.contains(MuscleGroup.TRICEPS));
		assertTrue(muscleGroups.contains(MuscleGroup.DELTS));
	}

}
