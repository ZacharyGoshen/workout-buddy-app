package com.zachgoshen.workoutbuddy.application.set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SetRepository;
import com.zachgoshen.workoutbuddy.domain.set.SetSortOrder;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

@Service
public class SetQueryService implements SetQueryUseCase {
	
	private final SetRepository setRepository;
	private final WorkoutRepository workoutRepository;
	
	public SetQueryService(SetRepository repository, WorkoutRepository workoutRepository) {
		this.setRepository = repository;
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
		Map<Set, Workout> setToWorkout = new HashMap<>();
		
		for (Set set : sets) {
			String setId = set.getId();
			Optional<Workout> workout = workoutRepository.findBySetId(setId);
			
			setToWorkout.put(set, workout.get());
		}
		
		return setToWorkout;
	}

}
