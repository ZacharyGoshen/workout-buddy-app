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
import com.zachgoshen.workouttracker.domain.set.specification.SetSpecifications;
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
	
	public List<SetWithWorkoutDetailsDto> findBy(SetQueryParameters parameters) {
		Specification<Set> specification = buildSpecification(parameters);
		
		List<Set> sets = findBySpecification(specification);
		
		Map<Set, Workout> setToWorkout = buildSetToWorkoutMap(sets);
		
		return setToWorkout.entrySet()
			.stream()
			.map(entry -> SetWithWorkoutDetailsDtoAssembler.assemble(entry.getKey(), entry.getValue()))
			.collect(Collectors.toList());
	}
	
	private Specification<Set> buildSpecification(SetQueryParameters parameters) {
		Specification<Set> specification = SetSpecifications.alwaysSatisfied();
		
		List<String> exerciseNames = parameters.getExerciseNames();
		if (!exerciseNames.isEmpty()) {
			specification = specification.and(SetSpecifications.containsAtLeastOneExercise(exerciseNames));
		}
		
		return specification;
	}
	
	private List<Set> findBySpecification(Specification<Set> specification) {
		return setRepository.findBy(specification)
			.stream()
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
