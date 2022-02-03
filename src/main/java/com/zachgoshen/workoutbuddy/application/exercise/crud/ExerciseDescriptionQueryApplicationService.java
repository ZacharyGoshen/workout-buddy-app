package com.zachgoshen.workoutbuddy.application.exercise.crud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionConverter;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workoutbuddy.domain.common.specification.AlwaysSatisfiedSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionSortOrder;
import com.zachgoshen.workoutbuddy.domain.exercise.description.PartialNameSpecification;

@Service
public class ExerciseDescriptionQueryApplicationService {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionQueryApplicationService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	public List<ExerciseDescriptionDto> findAll(String partialName, String sortBy) {
		Specification<ExerciseDescription> specification = new AlwaysSatisfiedSpecification<>();
		
		if (partialName != null) {
			PartialNameSpecification partialNameSpecification = new PartialNameSpecification(partialName);
			specification = specification.and(partialNameSpecification);
		}
		
		ExerciseDescriptionSortOrder sortOrder = getSortOrder(sortBy);
		
		return repository.findSortedBy(specification, sortOrder)
			.stream()
			.map(description -> ExerciseDescriptionConverter.toDto(description))
			.collect(Collectors.toList());
	}
	
	private static ExerciseDescriptionSortOrder getSortOrder(String sortBy) {
		if (sortBy != null && sortBy.equals("nameReverseAlphabetically")) {
			return ExerciseDescriptionSortOrder.NAME_REVERSE_ALPHABETICALLY;
		} else {
			return ExerciseDescriptionSortOrder.NAME_ALPHABETICALLY;
		}
	}

}
