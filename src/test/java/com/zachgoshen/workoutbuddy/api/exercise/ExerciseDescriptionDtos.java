package com.zachgoshen.workoutbuddy.api.exercise;

import java.util.Arrays;
import java.util.List;

public class ExerciseDescriptionDtos {
	
	public static ExerciseDescriptionDto benchPress() {
		ExerciseDescriptionDto benchPress = new ExerciseDescriptionDto();
		benchPress.setName("Bench Press");
		benchPress.setNotes("Bench Press Notes");
		
		List<String> muscleGroups = Arrays.asList("Delts", "Pecs", "Triceps");
		benchPress.setMuscleGroups(muscleGroups);
		
		return benchPress;
	}
	
	public static ExerciseDescriptionDto deadlift() {
		ExerciseDescriptionDto deadlift = new ExerciseDescriptionDto();
		deadlift.setName("Deadlift");
		deadlift.setNotes("Deadlift Notes");
		
		List<String> muscleGroups = Arrays.asList("Glutes", "Hamstrings", "Lower Back");
		deadlift.setMuscleGroups(muscleGroups);
		
		return deadlift;
	}

}
