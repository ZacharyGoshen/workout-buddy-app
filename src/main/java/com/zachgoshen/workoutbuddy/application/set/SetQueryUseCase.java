package com.zachgoshen.workoutbuddy.application.set;

import java.util.Map;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SetSortOrder;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public interface SetQueryUseCase {
	
	public Map<Set, Workout> findSortedBySpecification(Specification<Set> specification, SetSortOrder sortOrder);

}
