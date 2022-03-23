package com.zachgoshen.workoutbuddy.application.set;

import java.util.List;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.set.Set;

public interface SetRepository {
	
	public List<Set> findAll();
	
	public List<Set> findBy(Specification<Set> specification);
	
	public List<Set> findSortedBy(Specification<Set> specification, SetSortOrder order);

}
