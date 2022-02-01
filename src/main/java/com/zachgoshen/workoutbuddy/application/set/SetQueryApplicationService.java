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
public class SetQueryApplicationService {
	
	private final SetRepository setRepository;
	private final WorkoutRepository workoutRepository;
	
	public SetQueryApplicationService(SetRepository repository, WorkoutRepository workoutRepository) {
		this.setRepository = repository;
		this.workoutRepository = workoutRepository;
	}
	
	public List<SetWithWorkoutDetailsDto> findBy(SetSearchCriteriaDto criteria) {
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		SetSortOrder sortOrder = getSortOrder(criteria);
		
		List<Set> sets = setRepository.findSortedBy(specification, sortOrder)
			.stream()
			.collect(Collectors.toList());
		
		return buildSetWithWorkoutDetailsDtos(sets);
	}
	
	private static SetSortOrder getSortOrder(SetSearchCriteriaDto criteria) {
		String sortBy = criteria.getSortBy();
		
		if (sortBy != null && sortBy.equals("leastRecentCompletionTime")) {
			return SetSortOrder.LEAST_RECENT_COMPLETION_TIME;
		} else {
			return SetSortOrder.MOST_RECENT_COMPLETION_TIME;
		}
	}
	
	private List<SetWithWorkoutDetailsDto> buildSetWithWorkoutDetailsDtos(List<Set> sets) {
		Map<Set, Workout> setToWorkout = buildSetToWorkoutMap(sets);
		
		return sets
			.stream()
			.map(set -> SetWithWorkoutDetailsDtoAssembler.assemble(set, setToWorkout.get(set)))
			.collect(Collectors.toList());
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
