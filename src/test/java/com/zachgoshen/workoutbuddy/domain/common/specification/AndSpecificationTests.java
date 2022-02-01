package com.zachgoshen.workoutbuddy.domain.common.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.common.specification.AlwaysSatisfiedSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.AndSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.NeverSatisfiedSpecification;

public class AndSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_BothChildSpecificationsSatisfied_ReturnsTrue() {
		Object object = new Object();
		
		AlwaysSatisfiedSpecification<Object> specification1 = new AlwaysSatisfiedSpecification<>();
		AlwaysSatisfiedSpecification<Object> specification2 = new AlwaysSatisfiedSpecification<>();
		AndSpecification<Object> andSpecification = new AndSpecification<>(specification1, specification2);
		
		boolean isSatisfied = andSpecification.isSatisfiedBy(object);
		
		assertTrue(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_OnlyTheFirstChildSpecificationIsSatisfiedBy_ReturnsFalse() {
		Object object = new Object();
		
		AlwaysSatisfiedSpecification<Object> specification1 = new AlwaysSatisfiedSpecification<>();
		NeverSatisfiedSpecification<Object> specification2 = new NeverSatisfiedSpecification<>();
		AndSpecification<Object> andSpecification = new AndSpecification<>(specification1, specification2);
		
		boolean isSatisfied = andSpecification.isSatisfiedBy(object);
		
		assertFalse(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_OnlyTheSecondChildSpecificationIsSatisfiedBy_ReturnsFalse() {
		Object object = new Object();

		NeverSatisfiedSpecification<Object> specification1 = new NeverSatisfiedSpecification<>();
		AlwaysSatisfiedSpecification<Object> specification2 = new AlwaysSatisfiedSpecification<>();
		AndSpecification<Object> andSpecification = new AndSpecification<>(specification1, specification2);
		
		boolean isSatisfied = andSpecification.isSatisfiedBy(object);
		
		assertFalse(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_NeitherChildSpecificationSatisfied_ReturnsFalse() {
		Object object = new Object();

		NeverSatisfiedSpecification<Object> specification1 = new NeverSatisfiedSpecification<>();
		NeverSatisfiedSpecification<Object> specification2 = new NeverSatisfiedSpecification<>();
		AndSpecification<Object> andSpecification = new AndSpecification<>(specification1, specification2);
		
		boolean isSatisfied = andSpecification.isSatisfiedBy(object);
		
		assertFalse(isSatisfied);
	}

}
