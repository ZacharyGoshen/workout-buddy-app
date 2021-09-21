package com.zachgoshen.workouttracker.domain.workout.set;

import java.util.List;

import com.zachgoshen.workouttracker.domain.common.specification.Specification;

public interface SetRepository {
	
	public List<Set> findAll();
	
	public List<Set> findBy(Specification<Set> specification);

}
