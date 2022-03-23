package com.zachgoshen.workoutbuddy.data;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutRepository;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.Superset;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class DataPopulator {
	
	private final WorkoutRepository workoutRepository;
	private final ExerciseDescriptionRepository exerciseDescriptionRepository;
	
	public DataPopulator(WorkoutRepository workoutRepository, ExerciseDescriptionRepository exerciseDescriptionRepository) {
		this.workoutRepository = workoutRepository;
		this.exerciseDescriptionRepository = exerciseDescriptionRepository;
	}
	
	public void populate() throws InvalidRangeException {
		populateExerciseDescriptions();
		populateWorkouts();
	}
	
	private void populateExerciseDescriptions() {
		ExerciseDescription sumoDeadliftDescription = new ExerciseDescription("Sumo Deadlift");
		sumoDeadliftDescription.addMuscleGroup(MuscleGroup.GLUTES);
		sumoDeadliftDescription.addMuscleGroup(MuscleGroup.HAMSTRINGS);
		sumoDeadliftDescription.addMuscleGroup(MuscleGroup.LATS);
		sumoDeadliftDescription.addMuscleGroup(MuscleGroup.LOWER_BACK);
		exerciseDescriptionRepository.save(sumoDeadliftDescription);
		
		ExerciseDescription hamstringCurlDescription = new ExerciseDescription("Hamstring Curl");
		hamstringCurlDescription.setNotes("Fully extend legs.");
		hamstringCurlDescription.addMuscleGroup(MuscleGroup.BICEPS);
		exerciseDescriptionRepository.save(hamstringCurlDescription);
		
		ExerciseDescription latPulldownDescription = new ExerciseDescription("Lat Pulldown");
		latPulldownDescription.setNotes("Use wrist straps.");
		latPulldownDescription.addMuscleGroup(MuscleGroup.BICEPS);
		latPulldownDescription.addMuscleGroup(MuscleGroup.LATS);
		exerciseDescriptionRepository.save(latPulldownDescription);
		
		ExerciseDescription pullupDescription = new ExerciseDescription("Pullup");
		pullupDescription.addMuscleGroup(MuscleGroup.BICEPS);
		pullupDescription.addMuscleGroup(MuscleGroup.LATS);
		exerciseDescriptionRepository.save(pullupDescription);
		
		ExerciseDescription chestSupportedRowDescription = new ExerciseDescription("Chest Supported Row");
		chestSupportedRowDescription.setNotes("Use wrist straps.");
		chestSupportedRowDescription.addMuscleGroup(MuscleGroup.BICEPS);
		chestSupportedRowDescription.addMuscleGroup(MuscleGroup.LATS);
		exerciseDescriptionRepository.save(chestSupportedRowDescription);
		
		ExerciseDescription ezBarCurlDescription = new ExerciseDescription("EZ Bar Curl");
		ezBarCurlDescription.addMuscleGroup(MuscleGroup.BICEPS);
		exerciseDescriptionRepository.save(ezBarCurlDescription);
		
		ExerciseDescription dumbbellCurlDescription = new ExerciseDescription("Dumbbell Curl");
		dumbbellCurlDescription.addMuscleGroup(MuscleGroup.BICEPS);
		exerciseDescriptionRepository.save(dumbbellCurlDescription);
	}
	
	private void populateWorkouts() throws InvalidRangeException {
		populatePullDay1();
	}
	
	private void populatePullDay1() throws InvalidRangeException {
		Workout workout = new Workout();
		workout.setName("Pull Day 1");
		workout.setTimeCompleted(new Date());
		Map<String, ExerciseDescription> nameToExerciseDescription = new HashMap<>();
		
		List<ExerciseDescription> exerciseDescriptions = exerciseDescriptionRepository.findAll();
		
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
		
		workoutRepository.save(workout);
	}
	
	private static Date buildDateNMinutesAgo(int n) {
		LocalDateTime localDateTime = LocalDateTime.now().minusMinutes(n);
		ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
		Instant instant = zonedDateTime.toInstant();
		
		return Date.from(instant);
	}

}
