package com.zachgoshen.workoutbuddy.domain.common.specification;

public class NeverSatisfiedSpecification<T> extends Specification<T> {

	@Override
	public boolean isSatisfiedBy(T candidate) {
		return false;
	}

}
