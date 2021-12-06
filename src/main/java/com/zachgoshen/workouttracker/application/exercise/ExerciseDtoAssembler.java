package com.zachgoshen.workouttracker.application.exercise;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.exercise.Exercise;

public class ExerciseDtoAssembler {
	
	public static ExerciseDto assemble(Exercise exercise) {
		ExerciseDto dto = new ExerciseDto();
		
		dto.setName(exercise.getDescription().getName());
		
		Optional<Float> weightUsed = exercise.getWeightUsed();
		if (weightUsed.isPresent()) {
			dto.setWeightUsed(weightUsed.get());
		}
		
		Optional<Float> minimumWeightAllowed = exercise.getMinimumWeightAllowed();
		if (minimumWeightAllowed.isPresent()) {
			dto.setMinimumWeightAllowed(minimumWeightAllowed.get());
		}
		
		Optional<Float> maximumWeightAllowed = exercise.getMaximumWeightAllowed();
		if (maximumWeightAllowed.isPresent()) {
			dto.setMaximumWeightAllowed(maximumWeightAllowed.get());
		}
		
		Optional<Integer> repsCompleted = exercise.getRepsCompleted();
		if (repsCompleted.isPresent()) {
			dto.setRepsCompleted(repsCompleted.get());
		}
		
		Optional<Integer> minimumRepsAllowed = exercise.getMinimumRepsAllowed();
		if (minimumRepsAllowed.isPresent()) {
			dto.setMinimumRepsAllowed(minimumRepsAllowed.get());
		}
		
		Optional<Integer> maximumRepsAllowed = exercise.getMaximumRepsAllowed();
		if (maximumRepsAllowed.isPresent()) {
			dto.setMaximumRepsAllowed(maximumRepsAllowed.get());
		}
		
		Optional<Float> timePerformed = exercise.getTimePerformed();
		if (timePerformed.isPresent()) {
			dto.setTimePerformed(timePerformed.get());
		}
		
		Optional<Float> minimumDurationAllowed = exercise.getMinimumDurationAllowed();
		if (minimumDurationAllowed.isPresent()) {
			dto.setMinimumDurationAllowed(minimumDurationAllowed.get());
		}
		
		Optional<Float> maximumDurationAllowed = exercise.getMaximumDurationAllowed();
		if (maximumDurationAllowed.isPresent()) {
			dto.setMaximumDurationAllowed(maximumDurationAllowed.get());
		}
		
		return dto;
	}

}
