package com.zachgoshen.workoutbuddy.data.workout;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.Superset;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

public class MockWorkoutRepository implements WorkoutRepository {
	
	private final ExerciseDescriptionRepository exerciseDescriptionRepository;
	
	private final List<Workout> workouts;
	
	public MockWorkoutRepository(ExerciseDescriptionRepository exerciseDescriptionRepository) throws InvalidRangeException {
		this.exerciseDescriptionRepository = exerciseDescriptionRepository;
		
		workouts = buildWorkouts();
	}
	
	private List<Workout> buildWorkouts() throws InvalidRangeException {
		List<Workout> workouts = new ArrayList<>();
		
		workouts.add(buildPullDay1());
		
		return workouts;
	}
	
	private Workout buildPullDay1() throws InvalidRangeException {
		Workout workout = new Workout();
		workout.setName("Pull Day 1");
		workout.setTimeCompleted(new Date());

		List<ExerciseDescription> exerciseDescriptions = exerciseDescriptionRepository.findAll();
		Map<String, ExerciseDescription> nameToExerciseDescription = new HashMap<>();
		
		for (ExerciseDescription exerciseDescription : exerciseDescriptions) {
			String name = exerciseDescription.getName();
			nameToExerciseDescription.put(name, exerciseDescription);
		}
		
		ExerciseDescription sumoDeadliftDescription = nameToExerciseDescription.get("Sumo Deadlift");
		ExerciseDescription hamstringCurlDescription =  nameToExerciseDescription.get("Hamstring Curl");
		ExerciseDescription latPulldownDescription =  nameToExerciseDescription.get("Lat Pulldown");
		ExerciseDescription pullupDescription =  nameToExerciseDescription.get("Pullup");
		ExerciseDescription chestSupportedRowDescription =  nameToExerciseDescription.get("Chest Supported Row");
		ExerciseDescription ezBarCurlDescription =  nameToExerciseDescription.get("EZ Bar Curl");
		ExerciseDescription dumbbellCurlDescription =  nameToExerciseDescription.get("Dumbbell Curl");

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
		sumoDeadliftSet.setTimeCompleted(buildDateNMinutesAgo(50));
		
		Set hamstringCurlSet = new SingleExerciseSet(hamstringCurl);
		hamstringCurlSet.addMaximumRestTimeConstraint(60);
		hamstringCurlSet.setTimeCompleted(buildDateNMinutesAgo(40));
		
		Set latPulldownAndPullupSuperset = new Superset(Arrays.asList(latPulldown, pullup));
		latPulldownAndPullupSuperset.addMaximumRestTimeConstraint(60);
		latPulldownAndPullupSuperset.setTimeCompleted(buildDateNMinutesAgo(30));
		
		Set chestSupportedRowSet = new SingleExerciseSet(chestSupportedRow);
		chestSupportedRowSet.addMaximumRestTimeConstraint(60);
		chestSupportedRowSet.setTimeCompleted(buildDateNMinutesAgo(20));
		
		Set ezBarCurlSet = new SingleExerciseSet(ezBarCurl);
		ezBarCurlSet.addMaximumRestTimeConstraint(60);
		ezBarCurlSet.setTimeCompleted(buildDateNMinutesAgo(10));
		
		Set dumbbellCurlSet = new SingleExerciseSet(dumbbellCurl);
		dumbbellCurlSet.addMaximumRestTimeConstraint(60);
		dumbbellCurlSet.setTimeCompleted(buildDateNMinutesAgo(0));
		
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
	
	@Override
	public void save(Workout workout) {
		if (!workouts.contains(workout)) {
			workouts.add(workout);
		}
	}

	@Override
	public void deleteById(String id) {
		Optional<Workout> workout = findById(id);
		
		if (workout.isPresent()) {
			workouts.remove(workout.get());
		}
	}
	
	private static Date buildDateNMinutesAgo(int n) {
		LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(n);
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
		Instant instant = zonedDateTime.toInstant();
		
		return Date.from(instant);
	}

}
