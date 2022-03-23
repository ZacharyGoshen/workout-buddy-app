package com.zachgoshen.workoutbuddy.application.set;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zachgoshen.workoutbuddy.application.workout.WorkoutRepository;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class SetQueryService implements SetQueryUseCase {
	
	private final SetRepository setRepository;
	private final WorkoutRepository workoutRepository;
	
	public SetQueryService(SetRepository setRepository, WorkoutRepository workoutRepository) {
		this.setRepository = setRepository;
		this.workoutRepository = workoutRepository;
	}
	
	@Override
	public Map<Set, Workout> findSortedBySpecification(Specification<Set> specification, SetSortOrder sortOrder) {
		List<Set> sets = setRepository.findSortedBy(specification, sortOrder)
			.stream()
			.collect(Collectors.toList());
		
		return buildSetToWorkoutMap(sets);
	}
	
	private Map<Set, Workout> buildSetToWorkoutMap(List<Set> sets) {
		Map<Set, Workout> setToWorkout = new LinkedHashMap<>();
		
		for (Set set : sets) {
			String setId = set.getId();
			Optional<Workout> workout = workoutRepository.findBySetId(setId);
			
			setToWorkout.put(set, workout.get());
		}
		
		return setToWorkout;
	}

}
