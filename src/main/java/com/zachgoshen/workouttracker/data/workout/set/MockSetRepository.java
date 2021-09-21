package com.zachgoshen.workouttracker.data.workout.set;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.WorkoutRepository;
import com.zachgoshen.workouttracker.domain.workout.set.Set;
import com.zachgoshen.workouttracker.domain.workout.set.SetRepository;

@Repository
public class MockSetRepository implements SetRepository {
	
	private final WorkoutRepository workoutRepository;

	public MockSetRepository(WorkoutRepository workoutRepository) {
		this.workoutRepository = workoutRepository;
	}
	
	@Override
	public List<Set> findAll() {
		List<Workout> allWorkouts = workoutRepository.findAll();
		
		return allWorkouts.stream()
			.flatMap(workout -> workout.getSets().stream())
			.collect(Collectors.toList());
	}

	@Override
	public List<Set> findBy(Specification<Set> specification) {
		List<Set> allSets = findAll();
		
		return allSets.stream()
			.filter(set -> specification.isSatisfiedBy(set))
			.collect(Collectors.toList());
	}

}
