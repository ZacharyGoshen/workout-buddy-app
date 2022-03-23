package com.zachgoshen.workoutbuddy.domain.exercise;

import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptions;

public class Exercises {
	
	public static Exercise benchPress() throws InvalidRangeException {
		ExerciseDescription benchPressDescription = ExerciseDescriptions.benchPress();
		
		Exercise benchPress = new Exercise(benchPressDescription);
		
		benchPress.addBoundedRepsConstraint(6, 8);
		benchPress.setRepsCompleted(8);
		
		benchPress.addBoundedWeightConstraint(225, 225);
		benchPress.setWeightUsed(225f);
		
		benchPress.addBoundedDurationConstraint(30, 60);
		benchPress.setTimePerformed(45f);
		
		return benchPress;
	}
	
	public static Exercise deadlift() throws InvalidRangeException {
		ExerciseDescription deadliftDescription = ExerciseDescriptions.deadlift();
		
		Exercise deadlift = new Exercise(deadliftDescription);
		
		deadlift.addBoundedRepsConstraint(5, 5);
		deadlift.setRepsCompleted(5);
		
		deadlift.addBoundedWeightConstraint(315, 315);
		deadlift.setWeightUsed(315f);
		
		deadlift.addBoundedDurationConstraint(30, 90);
		deadlift.setTimePerformed(60f);
		
		return deadlift;
	}
	
	public static Exercise dip() throws InvalidRangeException {
		ExerciseDescription dipDescription = ExerciseDescriptions.dip();
		
		Exercise dip = new Exercise(dipDescription);
		
		dip.addMinimumRepsConstraint(0);
		dip.setRepsCompleted(20);
		
		return dip;
	}
	
	public static Exercise squat() throws InvalidRangeException {
		ExerciseDescription squatDescription = ExerciseDescriptions.squat();
		
		Exercise squat = new Exercise(squatDescription);
		
		squat.addBoundedRepsConstraint(8, 10);
		squat.setRepsCompleted(10);
		
		squat.addBoundedWeightConstraint(135, 135);
		squat.setWeightUsed(135f);
		
		squat.addBoundedDurationConstraint(60, 90);
		squat.setTimePerformed(75f);
		
		return squat;
	}

}
