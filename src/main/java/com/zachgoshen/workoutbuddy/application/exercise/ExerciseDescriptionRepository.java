package com.zachgoshen.workoutbuddy.application.exercise;

import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionSortOrder;

public interface ExerciseDescriptionRepository {
	
	public List<ExerciseDescription> findAll();
	
	public List<ExerciseDescription> findSortedBy(Specification<ExerciseDescription> specification, ExerciseDescriptionSortOrder sortOrder);
	
	public Optional<ExerciseDescription> findById(String id);
	
	public void save(ExerciseDescription description);
	
	public void deleteAll();
	
	public void deleteById(String id);
	
	public boolean existsById(String id);

}
