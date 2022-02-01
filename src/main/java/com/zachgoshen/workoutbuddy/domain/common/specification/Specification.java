package com.zachgoshen.workoutbuddy.domain.common.specification;

public abstract class Specification<T> {
	
	public abstract boolean isSatisfiedBy(T candidate);
	
	public Specification<T> and(Specification<T> otherSpecification) {
		return new AndSpecification<>(this, otherSpecification);
	}
	
	public Specification<T> or(Specification<T> otherSpecification) {
		return new OrSpecification<>(this, otherSpecification);
	}
	
	public Specification<T> not(Specification<T> specification) {
		return new NotSpecification<>(specification);
	}

}
