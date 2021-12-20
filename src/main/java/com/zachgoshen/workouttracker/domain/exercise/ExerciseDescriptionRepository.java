package com.zachgoshen.workouttracker.domain.exercise;

import java.util.List;

public interface ExerciseDescriptionRepository {
	
	public List<ExerciseDescription> findAll();

}
