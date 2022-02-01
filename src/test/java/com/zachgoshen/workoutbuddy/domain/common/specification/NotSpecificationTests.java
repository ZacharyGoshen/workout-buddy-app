package com.zachgoshen.workoutbuddy.domain.common.specification;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.domain.common.specification.AlwaysSatisfiedSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.NeverSatisfiedSpecification;
import com.zachgoshen.workoutbuddy.domain.common.specification.NotSpecification;

public class NotSpecificationTests {
	
	@Test
	public void IsSatisfiedBy_ChildSpecificationSatisfied_ReturnsFalse() {
		Object object = new Object();
		
		AlwaysSatisfiedSpecification<Object> specification = new AlwaysSatisfiedSpecification<>();
		NotSpecification<Object> notSpecification = new NotSpecification<>(specification);
		
		boolean isSatisfied = notSpecification.isSatisfiedBy(object);
		
		assertFalse(isSatisfied);
	}
	
	@Test
	public void IsSatisfiedBy_ChildSpecificationNotSatisfied_ReturnsTrue() {
		Object object = new Object();
		
		NeverSatisfiedSpecification<Object> specification = new NeverSatisfiedSpecification<>();
		NotSpecification<Object> notSpecification = new NotSpecification<>(specification);
		
		boolean isSatisfied = notSpecification.isSatisfiedBy(object);
		
		assertTrue(isSatisfied);
	}

}
