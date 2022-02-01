package com.zachgoshen.workoutbuddy.domain.common.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.common.specification.NeverSatisfiedSpecification;

public class NeverSatisfiedSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ReturnsFalse() {
		Object object = new Object();
		
		NeverSatisfiedSpecification<Object> specification = new NeverSatisfiedSpecification<>();
		
		boolean isSatisfied = specification.isSatisfiedBy(object);
		
		assertFalse(isSatisfied);
	}

}
