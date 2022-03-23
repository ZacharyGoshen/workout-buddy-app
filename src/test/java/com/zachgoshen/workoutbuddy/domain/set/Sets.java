package com.zachgoshen.workoutbuddy.domain.set;

import java.util.Arrays;
import java.util.List;

import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercises;

public class Sets {
	
	public static SingleExerciseSet benchPressSet() throws InvalidRangeException {
		Exercise benchPress = Exercises.benchPress();
		
		SingleExerciseSet benchPressSet = new SingleExerciseSet(benchPress);
		
		benchPressSet.addBoundedRestTimeConstraint(180, 180);
		benchPressSet.setTimeRested(180f);
		
		return benchPressSet;
	}
	
	public static SingleExerciseSet deadliftSet() throws InvalidRangeException {
		Exercise deadlift = Exercises.deadlift();
		
		SingleExerciseSet deadliftSet = new SingleExerciseSet(deadlift);
		
		deadliftSet.addBoundedRestTimeConstraint(300, 300);
		deadliftSet.setTimeRested(300f);
		
		return deadliftSet;
	}
	
	public static SingleExerciseSet squatSet() throws InvalidRangeException {
		Exercise squat = Exercises.squat();
		
		SingleExerciseSet squatSet = new SingleExerciseSet(squat);
		
		squatSet.addBoundedRestTimeConstraint(60, 90);
		squatSet.setTimeRested(75f);
		
		return squatSet;
	}
	
	public static Superset benchPressIntoDipSuperset() throws InvalidRangeException {
		Exercise benchPress = Exercises.benchPress();
		Exercise dip = Exercises.dip();
		List<Exercise> exercises = Arrays.asList(benchPress, dip);
		
		Superset benchPressIntoDipSuperset = new Superset(exercises);
		
		benchPressIntoDipSuperset.addBoundedRestTimeConstraint(60, 60);
		benchPressIntoDipSuperset.setTimeRested(60f);
		
		return benchPressIntoDipSuperset;
	}

}
