package com.zachgoshen.workoutbuddy.data.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

public class MockWorkoutRepository implements WorkoutRepository {
	
	private final List<Workout> workouts;
	
	public MockWorkoutRepository() {
		workouts = new ArrayList<>();
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

}
