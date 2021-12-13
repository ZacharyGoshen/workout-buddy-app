package com.zachgoshen.workouttracker.data.workout;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;
import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.WorkoutRepository;

@Repository
public class MockWorkoutRepository implements WorkoutRepository {
	
	private final List<Workout> workouts;
	
	public MockWorkoutRepository() throws InvalidRangeException {
		workouts = buildWorkouts();
	}
	
	private static List<Workout> buildWorkouts() throws InvalidRangeException {
		return Arrays.asList(buildPullDay1());
	}
	
	private static Workout buildPullDay1() throws InvalidRangeException {
		Workout workout = new Workout();
		workout.setName("Pull Day 1");
		workout.setTimeCompleted(new Date());
		
		System.out.println(workout.getId());
		
		ExerciseDescription sumoDeadliftDescription = new ExerciseDescription("Sumo Deadlift");
		ExerciseDescription hamstringCurlDescription = new ExerciseDescription("Hamstring Curl");
		ExerciseDescription latPulldownDescription = new ExerciseDescription("Lat Pulldown");
		ExerciseDescription pullupDescription = new ExerciseDescription("Pullup");
		ExerciseDescription chestSupportedRowDescription = new ExerciseDescription("Chest Supported Row");
		ExerciseDescription ezBarCurlDescription = new ExerciseDescription("EZ Bar Curl");
		ExerciseDescription dumbbellCurlDescription = new ExerciseDescription("Dumbbell Curl");

		Exercise sumoDeadlift = new Exercise(sumoDeadliftDescription);
		sumoDeadlift.addBoundedRepsConstraint(6, 8);
		sumoDeadlift.addMinimumWeightConstraint(135);
		
		Exercise hamstringCurl = new Exercise(hamstringCurlDescription);
		hamstringCurl.addBoundedRepsConstraint(8, 10);
		hamstringCurl.addMinimumWeightConstraint(120);
		
		Exercise latPulldown = new Exercise(latPulldownDescription);
		latPulldown.addBoundedRepsConstraint(8, 10);
		latPulldown.addMinimumWeightConstraint(100);
		
		Exercise pullup = new Exercise(pullupDescription);
		latPulldown.addBoundedRepsConstraint(8, 10);
		
		Exercise chestSupportedRow = new Exercise(chestSupportedRowDescription);
		chestSupportedRow.addBoundedRepsConstraint(8, 10);
		chestSupportedRow.addMinimumWeightConstraint(90);
		
		Exercise ezBarCurl = new Exercise(ezBarCurlDescription);
		ezBarCurl.addBoundedRepsConstraint(8, 10);
		ezBarCurl.addMinimumWeightConstraint(40);
		
		Exercise dumbbellCurl = new Exercise(dumbbellCurlDescription);
		dumbbellCurl.addBoundedRepsConstraint(8, 10);
		dumbbellCurl.addMinimumWeightConstraint(15);

		Set sumoDeadliftSet = new SingleExerciseSet(sumoDeadlift);
		sumoDeadliftSet.addMaximumRestTimeConstraint(180);
		
		Set hamstringCurlSet = new SingleExerciseSet(hamstringCurl);
		hamstringCurlSet.addMaximumRestTimeConstraint(60);
		
		Set latPulldownAndPullupSuperset = new Superset(Arrays.asList(latPulldown, pullup));
		latPulldownAndPullupSuperset.addMaximumRestTimeConstraint(60);
		
		Set chestSupportedRowSet = new SingleExerciseSet(chestSupportedRow);
		chestSupportedRowSet.addMaximumRestTimeConstraint(60);
		
		Set ezBarCurlSet = new SingleExerciseSet(ezBarCurl);
		ezBarCurlSet.addMaximumRestTimeConstraint(60);
		
		Set dumbbellCurlSet = new SingleExerciseSet(dumbbellCurl);
		dumbbellCurlSet.addMaximumRestTimeConstraint(60);
		
		workout.appendSet(sumoDeadliftSet);
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.appendSet(hamstringCurlSet);
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.appendSet(latPulldownAndPullupSuperset);
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.appendSet(chestSupportedRowSet);
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.appendSet(ezBarCurlSet);
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		workout.appendSet(dumbbellCurlSet);
		workout.duplicateLastSet();
		workout.duplicateLastSet();
		
		return workout;
	}

	@Override
	public List<Workout> findAll() {
		return workouts;
	}

	@Override
	public Optional<Workout> findById(String id) {
		return workouts.stream()
			.filter(workout -> workout.getId().equals(id))
			.findFirst();
	}

	@Override
	public Optional<Workout> findBySetId(String setId) {
		return workouts.stream()
			.filter(workout -> workout.containsSet(setId))
			.findFirst();
	}

}
