package com.zachgoshen.workouttracker.domain.common.specification;

public class OrSpecification<T> extends Specification<T> {
	
	private final Specification<T> firstSpecification;
	private final Specification<T> secondSpecification;
	
	public OrSpecification(Specification<T> firstSpecification, Specification<T> secondSpecification) {
		this.firstSpecification = firstSpecification;
		this.secondSpecification = secondSpecification;
	}

	@Override
	public boolean isSatisfiedBy(T candidate) {
		return firstSpecification.isSatisfiedBy(candidate) || secondSpecification.isSatisfiedBy(candidate);
	}

}
