package com.zachgoshen.workouttracker.domain.common.specification;

public class AlwaysSatisfiedSpecification<T> extends Specification<T> {

	@Override
	public boolean isSatisfiedBy(T candidate) {
		return true;
	}

}
