package com.zachgoshen.workoutbuddy.domain.common.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class OrSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_BothChildSpecificationsSatisfied_ReturnsTrue() {
		Object object = new Object();
		
		AlwaysSatisfiedSpecification<Object> specification1 = new AlwaysSatisfiedSpecification<>();
		AlwaysSatisfiedSpecification<Object> specification2 = new AlwaysSatisfiedSpecification<>();
		OrSpecification<Object> orSpecification = new OrSpecification<>(specification1, specification2);
		
		boolean isSatisfied = orSpecification.isSatisfiedBy(object);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_OnlyTheFirstChildSpecificationIsSatisfiedBy_ReturnsTrue() {
		Object object = new Object();
		
		AlwaysSatisfiedSpecification<Object> specification1 = new AlwaysSatisfiedSpecification<>();
		NeverSatisfiedSpecification<Object> specification2 = new NeverSatisfiedSpecification<>();
		OrSpecification<Object> orSpecification = new OrSpecification<>(specification1, specification2);
		
		boolean isSatisfied = orSpecification.isSatisfiedBy(object);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_OnlyTheSecondChildSpecificationIsSatisfiedBy_ReturnsTrue() {
		Object object = new Object();

		NeverSatisfiedSpecification<Object> specification1 = new NeverSatisfiedSpecification<>();
		AlwaysSatisfiedSpecification<Object> specification2 = new AlwaysSatisfiedSpecification<>();
		OrSpecification<Object> orSpecification = new OrSpecification<>(specification1, specification2);
		
		boolean isSatisfied = orSpecification.isSatisfiedBy(object);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_NeitherChildSpecificationSatisfied_ReturnsFalse() {
		Object object = new Object();

		NeverSatisfiedSpecification<Object> specification1 = new NeverSatisfiedSpecification<>();
		NeverSatisfiedSpecification<Object> specification2 = new NeverSatisfiedSpecification<>();
		OrSpecification<Object> orSpecification = new OrSpecification<>(specification1, specification2);
		
		boolean isSatisfied = orSpecification.isSatisfiedBy(object);
		
		assertFalse(isSatisfied);
	}

}
