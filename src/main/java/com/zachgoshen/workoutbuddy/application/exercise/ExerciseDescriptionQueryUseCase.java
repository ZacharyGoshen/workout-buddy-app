package com.zachgoshen.workoutbuddy.application.exercise;

import java.util.List;

import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionSortOrder;

public interface ExerciseDescriptionQueryUseCase {

	public List<ExerciseDescription> findSortedByPartialName(String partialName, ExerciseDescriptionSortOrder sortOrder);

}
