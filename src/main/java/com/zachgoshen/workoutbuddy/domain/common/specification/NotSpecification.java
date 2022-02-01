package com.zachgoshen.workoutbuddy.domain.common.specification;

public class NotSpecification<T> extends Specification<T> {
	
	private final Specification<T> specification;

	public NotSpecification(Specification<T> specification) {
		this.specification = specification;
	}

	@Override
	public boolean isSatisfiedBy(T candidate) {
		return !specification.isSatisfiedBy(candidate);
	}

}
