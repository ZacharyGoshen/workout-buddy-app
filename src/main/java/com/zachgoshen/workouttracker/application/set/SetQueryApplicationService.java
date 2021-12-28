package com.zachgoshen.workouttracker.application.set;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.application.exercise.ExerciseSearchFilterDto;
import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.specification.ExerciseSpecifications;
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
	
	public List<SetWithWorkoutDetailsDto> findAll() {
		List<Set> sets = setRepository.findAll()
			.stream()
			.collect(Collectors.toList());
		
		return buildSetWithWorkoutDetailsDtos(sets);
	}
	
	public List<SetWithWorkoutDetailsDto> findBy(SetSearchCriteriaDto criteria) {
		Specification<Set> specification = buildSetSpecification(criteria);
		
		List<Set> sets =  setRepository.findBy(specification)
			.stream()
			.collect(Collectors.toList());
		
		return buildSetWithWorkoutDetailsDtos(sets);
	}
	
	private static Specification<Set> buildSetSpecification(SetSearchCriteriaDto criteria) {
		Specification<Set> specification = SetSpecifications.alwaysSatisfied();
		
		List<Specification<Set>> containsSatisfyingExerciseSpecifications = buildContainsSatisfyingExerciseSpecifications(criteria);
		for (Specification<Set> containsSatisfyingExerciseSpecification : containsSatisfyingExerciseSpecifications) {
			specification = specification.and(containsSatisfyingExerciseSpecification);
		}
		
		Float minimumTimeRested = criteria.getMinimumTimeRested();
		if (minimumTimeRested != null) {
			specification = specification.and(SetSpecifications.timeRestedIsAtLeast(minimumTimeRested));
		}
		
		Float maximumTimeRested = criteria.getMaximumTimeRested();
		if (maximumTimeRested != null) {
			specification = specification.and(SetSpecifications.timeRestedIsAtMost(maximumTimeRested));
		}
		
		return specification;
	}
	
	private static List<Specification<Set>> buildContainsSatisfyingExerciseSpecifications(SetSearchCriteriaDto setCriteria) {
		return setCriteria.getExerciseFilters()
			.stream()
			.map(exerciseCriteria -> buildExerciseSpecification(exerciseCriteria))
			.map(exerciseSpecification -> SetSpecifications.containsSatisfyingExercise(exerciseSpecification))
			.collect(Collectors.toList());
	}
	
	private static Specification<Exercise> buildExerciseSpecification(ExerciseSearchFilterDto criteria) {
		Specification<Exercise> specification = ExerciseSpecifications.alwaysSatisfied();
		
		String name = criteria.getName();
		if (name != null) {
			specification = specification.and(ExerciseSpecifications.nameIs(name));
		}
		
		Float minimumWeightUsed = criteria.getMinimumWeightUsed();
		if (minimumWeightUsed != null) {
			specification = specification.and(ExerciseSpecifications.weightUsedIsAtLeast(minimumWeightUsed));
		}
		
		Float maximumWeightUsed = criteria.getMaximumWeightUsed();
		if (maximumWeightUsed != null) {
			specification = specification.and(ExerciseSpecifications.weightUsedIsAtMost(maximumWeightUsed));
		}
		
		Integer minimumRepsCompleted = criteria.getMinimumRepsCompleted();
		if (minimumRepsCompleted != null) {
			specification = specification.and(ExerciseSpecifications.repsCompletedIsAtLeast(minimumRepsCompleted));
		}
		
		Integer maximumRepsCompleted = criteria.getMaximumRepsCompleted();
		if (maximumRepsCompleted != null) {
			specification = specification.and(ExerciseSpecifications.repsCompletedIsAtMost(maximumRepsCompleted));
		}
		
		Float minimumTimePerformed = criteria.getMinimumTimePerformed();
		if (minimumTimePerformed != null) {
			specification = specification.and(ExerciseSpecifications.timePerformedIsAtLeast(minimumTimePerformed));
		}
		
		Float maximumTimePerformed = criteria.getMaximumTimePerformed();
		if (maximumTimePerformed != null) {
			specification = specification.and(ExerciseSpecifications.timePerformedIsAtMost(maximumTimePerformed));
		}
		
		return specification;
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
