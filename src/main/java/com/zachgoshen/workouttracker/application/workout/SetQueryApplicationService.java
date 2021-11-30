package com.zachgoshen.workouttracker.application.workout;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;
import com.zachgoshen.workouttracker.domain.workout.set.Set;
import com.zachgoshen.workouttracker.domain.workout.set.SetRepository;
import com.zachgoshen.workouttracker.domain.workout.set.SetSpecifications;

@Service
public class SetQueryApplicationService {
	
	private final SetRepository repository;
	
	public SetQueryApplicationService(SetRepository repository) {
		this.repository = repository;
	}
	
	public List<SetDto> findBy(SetQueryParameters parameters) {
		Specification<Set> specification = SetSpecifications.alwaysSatisfied();
		
		List<String> exerciseNames = parameters.getExerciseNames();
		if (!exerciseNames.isEmpty()) {
			specification = specification.and(SetSpecifications.containsAtLeastOneExercise(exerciseNames));
		}
		
		return repository.findBy(specification)
			.stream()
			.map(set -> SetDtoAssembler.assemble(set))
			.collect(Collectors.toList());
	}

}
