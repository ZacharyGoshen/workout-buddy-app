package com.zachgoshen.workoutbuddy.domain.common.specification;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.common.specification.AlwaysSatisfiedSpecification;

public class AlwaysSatisfiedSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ReturnsTrue() {
		Object object = new Object();
		
		AlwaysSatisfiedSpecification<Object> specification = new AlwaysSatisfiedSpecification<>();
		
		boolean isSatisfied = specification.isSatisfiedBy(object);
		
		assertTrue(isSatisfied);
	}

}
