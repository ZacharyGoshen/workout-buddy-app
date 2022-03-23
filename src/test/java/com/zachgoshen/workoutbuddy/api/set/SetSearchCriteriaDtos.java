package com.zachgoshen.workoutbuddy.api.set;

import java.util.Arrays;
import java.util.List;

import com.zachgoshen.workoutbuddy.api.exercise.ExerciseSearchFilterDto;

public class SetSearchCriteriaDtos {
	
	public static SetSearchCriteriaDto byMinimumTimeRested(Float minimumTimeRested) {
		SetSearchCriteriaDto searchCriteria = new SetSearchCriteriaDto();
		searchCriteria.setMinimumTimeRested(minimumTimeRested);
		
		return searchCriteria;
	}
	
	public static SetSearchCriteriaDto byMaximumTimeRested(Float maximumTimeRested) {
		SetSearchCriteriaDto searchCriteria = new SetSearchCriteriaDto();
		searchCriteria.setMaximumTimeRested(maximumTimeRested);
		
		return searchCriteria;
	}
	
	public static SetSearchCriteriaDto byExerciseName(String exerciseName) {
		ExerciseSearchFilterDto exerciseFilter = new ExerciseSearchFilterDto();
		exerciseFilter.setName(exerciseName);
		
		return buildCriteriaWithExerciseFilter(exerciseFilter);
	}
	
	public static SetSearchCriteriaDto byMinimumWeightUsed(Float minimumWeightUsed) {
		ExerciseSearchFilterDto exerciseFilter = new ExerciseSearchFilterDto();
		exerciseFilter.setMinimumWeightUsed(minimumWeightUsed);
		
		return buildCriteriaWithExerciseFilter(exerciseFilter);
	}
	
	public static SetSearchCriteriaDto byMaximumWeightUsed(Float maximumWeightUsed) {
		ExerciseSearchFilterDto exerciseFilter = new ExerciseSearchFilterDto();
		exerciseFilter.setMaximumWeightUsed(maximumWeightUsed);
		
		return buildCriteriaWithExerciseFilter(exerciseFilter);
	}
	
	public static SetSearchCriteriaDto byMinimumRepsCompleted(Integer minimumRepsCompleted) {
		ExerciseSearchFilterDto exerciseFilter = new ExerciseSearchFilterDto();
		exerciseFilter.setMinimumRepsCompleted(minimumRepsCompleted);
		
		return buildCriteriaWithExerciseFilter(exerciseFilter);
	}
	
	public static SetSearchCriteriaDto byMaximumRepsCompleted(Integer maximumRepsCompleted) {
		ExerciseSearchFilterDto exerciseFilter = new ExerciseSearchFilterDto();
		exerciseFilter.setMaximumRepsCompleted(maximumRepsCompleted);
		
		return buildCriteriaWithExerciseFilter(exerciseFilter);
	}
	
	public static SetSearchCriteriaDto byMinimumTimePerformed(Float minimumTimePerformed) {
		ExerciseSearchFilterDto exerciseFilter = new ExerciseSearchFilterDto();
		exerciseFilter.setMinimumTimePerformed(minimumTimePerformed);
		
		return buildCriteriaWithExerciseFilter(exerciseFilter);
	}
	
	public static SetSearchCriteriaDto byMaximumTimePerformed(Float maximumTimePerformed) {
		ExerciseSearchFilterDto exerciseFilter = new ExerciseSearchFilterDto();
		exerciseFilter.setMaximumTimePerformed(maximumTimePerformed);
		
		return buildCriteriaWithExerciseFilter(exerciseFilter);
	}
	
	private static SetSearchCriteriaDto buildCriteriaWithExerciseFilter(ExerciseSearchFilterDto exerciseFilter) {
		SetSearchCriteriaDto searchCriteria = new SetSearchCriteriaDto();
		
		List<ExerciseSearchFilterDto> exerciseFilters = Arrays.asList(exerciseFilter);
		searchCriteria.setExerciseFilters(exerciseFilters);
		
		return searchCriteria;
	}

}
