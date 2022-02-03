package com.zachgoshen.workoutbuddy.domain.exercise.description;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;

public class PartialNameSpecification extends Specification<ExerciseDescription> {
	
	private final String partialName;

	public PartialNameSpecification(String partialName) {
		partialName = partialName.toLowerCase();
		partialName = partialName.replace(" ", "");
		this.partialName = partialName;
	}

	@Override
	public boolean isSatisfiedBy(ExerciseDescription candidate) {
		String name = candidate.getName();
		
		name = name.toLowerCase();
		name = name.replace(" ", "");
		
		return name.contains(partialName);
	}

}
