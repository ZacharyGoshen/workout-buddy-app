package com.zachgoshen.workoutbuddy.domain.exercise.description;

public class ExerciseDescriptions {
	
	public static ExerciseDescription benchPress() {
		ExerciseDescription benchPress = new ExerciseDescription("Bench Press");
		benchPress.setNotes("Bench Press Notes");
		benchPress.addMuscleGroup(MuscleGroup.DELTS);
		benchPress.addMuscleGroup(MuscleGroup.PECS);
		benchPress.addMuscleGroup(MuscleGroup.TRICEPS);
		
		return benchPress;
	}
	
	public static ExerciseDescription deadlift() {
		ExerciseDescription deadlift = new ExerciseDescription("Deadlift");
		deadlift.setNotes("Deadlift Notes");
		deadlift.addMuscleGroup(MuscleGroup.GLUTES);
		deadlift.addMuscleGroup(MuscleGroup.HAMSTRINGS);
		deadlift.addMuscleGroup(MuscleGroup.LOWER_BACK);
		
		return deadlift;
	}
	
	public static ExerciseDescription dip() {
		ExerciseDescription dip = new ExerciseDescription("Dip");
		dip.setNotes("Dip Notes");
		dip.addMuscleGroup(MuscleGroup.DELTS);
		dip.addMuscleGroup(MuscleGroup.PECS);
		dip.addMuscleGroup(MuscleGroup.TRICEPS);
		
		return dip;
	}
	
	public static ExerciseDescription squat() {
		ExerciseDescription squat = new ExerciseDescription("Squat");
		squat.setNotes("Squat Notes");
		squat.addMuscleGroup(MuscleGroup.GLUTES);
		squat.addMuscleGroup(MuscleGroup.HAMSTRINGS);
		squat.addMuscleGroup(MuscleGroup.LOWER_BACK);
		squat.addMuscleGroup(MuscleGroup.QUADS);
		
		return squat;
	}

}
