package com.zachgoshen.workoutbuddy.application.exercise;

import java.util.List;

import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDescriptionQueryUseCase;
import com.zachgoshen.workoutbuddy.domain.common.specification.AlwaysSatisfiedSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionSortOrder;
import com.zachgoshen.workoutbuddy.domain.exercise.description.PartialNameSpecification;

public class ExerciseDescriptionQueryService implements ExerciseDescriptionQueryUseCase {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionQueryService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public List<ExerciseDescription> findSortedByPartialName(String partialName, ExerciseDescriptionSortOrder sortOrder) {
		Specification<ExerciseDescription> specification = new AlwaysSatisfiedSpecification<>();
		
		if (partialName != null) {
			PartialNameSpecification partialNameSpecification = new PartialNameSpecification(partialName);
			specification = specification.and(partialNameSpecification);
		}
		
		return repository.findSortedBy(specification, sortOrder);
	}

}
