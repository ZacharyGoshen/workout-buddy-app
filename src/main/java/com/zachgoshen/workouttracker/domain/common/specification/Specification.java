package com.zachgoshen.workouttracker.domain.common.specification;

public interface Specification<T> {
	
	public boolean isSatisfiedBy(T candidate);

}
