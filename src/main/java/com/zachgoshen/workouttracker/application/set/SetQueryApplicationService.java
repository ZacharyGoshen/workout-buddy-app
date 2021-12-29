package com.zachgoshen.workouttracker.application.set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SetRepository;
import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.WorkoutRepository;

@Service
public class SetQueryApplicationService {
	
	private final SetRepository setRepository;
	private final WorkoutRepository workoutRepository;
	
	public SetQueryApplicationService(SetRepository repository, WorkoutRepository workoutRepository) {
		this.setRepository = repository;
		this.workoutRepository = workoutRepository;
	}
	
	public List<SetWithWorkoutDetailsDto> findAll() {
		List<Set> sets = setRepository.findAll()
			.stream()
			.collect(Collectors.toList());
		
		return buildSetWithWorkoutDetailsDtos(sets);
	}
	
	public List<SetWithWorkoutDetailsDto> findBy(SetSearchCriteriaDto criteria) {
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		
		List<Set> sets = setRepository.findBy(specification)
			.stream()
			.collect(Collectors.toList());
		
		return buildSetWithWorkoutDetailsDtos(sets);
	}
	
	private List<SetWithWorkoutDetailsDto> buildSetWithWorkoutDetailsDtos(List<Set> sets) {
		Map<Set, Workout> setToWorkout = buildSetToWorkoutMap(sets);
		
		return setToWorkout.entrySet()
			.stream()
			.map(entry -> SetWithWorkoutDetailsDtoAssembler.assemble(entry.getKey(), entry.getValue()))
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
