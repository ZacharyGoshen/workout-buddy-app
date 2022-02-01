package com.zachgoshen.workouttracker.domain.exercise;

import java.util.List;
import java.util.Optional;

public interface ExerciseDescriptionRepository {
	
	public List<ExerciseDescription> findAll();
	
	public Optional<ExerciseDescription> findById(String id);
	
	public void save(ExerciseDescription description);
	
	public void deleteById(String id);
	
	public boolean existsById(String id);

}
