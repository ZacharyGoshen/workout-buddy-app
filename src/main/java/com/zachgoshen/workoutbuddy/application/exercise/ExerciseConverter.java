package com.zachgoshen.workoutbuddy.application.exercise;

import java.util.Optional;

import com.zachgoshen.workoutbuddy.application.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;

public class ExerciseConverter {
	
	public static ExerciseDto toDto(Exercise exercise) {
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
	
	public static Exercise toEntity(ExerciseDto dto) throws InvalidRangeException, DtoConversionException {
		ExerciseDescription description = new ExerciseDescription(dto.getName());
		
		Exercise exercise = new Exercise(description);
		
		exercise.setWeightUsed(dto.getWeightUsed());
		
		Float minimumWeightAllowed = dto.getMinimumWeightAllowed();
		Float maximumWeightAllowed = dto.getMaximumWeightAllowed();
		
		if (minimumWeightAllowed != null && maximumWeightAllowed != null) {
			exercise.addBoundedWeightConstraint(minimumWeightAllowed.floatValue(), maximumWeightAllowed.floatValue());
		} else if (minimumWeightAllowed != null && maximumWeightAllowed == null) {
			exercise.addMinimumWeightConstraint(minimumWeightAllowed.floatValue());
		} else if (minimumWeightAllowed == null && maximumWeightAllowed != null) {
			throw new DtoConversionException("Invalid weight constraint");
		}
		
		exercise.setRepsCompleted(dto.getRepsCompleted());
		
		Integer minimumRepsAllowed = dto.getMinimumRepsAllowed();
		Integer maximumRepsAllowed = dto.getMaximumRepsAllowed();
		
		if (minimumRepsAllowed != null && maximumRepsAllowed != null) {
			exercise.addBoundedRepsConstraint(minimumRepsAllowed.intValue(), maximumRepsAllowed.intValue());
		} else if (minimumRepsAllowed != null && maximumRepsAllowed == null) {
			exercise.addMinimumRepsConstraint(minimumRepsAllowed.intValue());
		} else if (minimumRepsAllowed == null && maximumRepsAllowed != null) {
			throw new DtoConversionException("Invalid reps constraint");
		}
		
		exercise.setTimePerformed(dto.getTimePerformed());
		
		Float minimumDurationAllowed = dto.getMinimumDurationAllowed();
		Float maximumDurationAllowed = dto.getMaximumDurationAllowed();
		
		if (minimumDurationAllowed != null && maximumDurationAllowed != null) {
			exercise.addBoundedDurationConstraint(minimumDurationAllowed.floatValue(), maximumDurationAllowed.floatValue());
		} else if (minimumDurationAllowed != null && maximumDurationAllowed == null) {
			exercise.addMinimumDurationConstraint(minimumDurationAllowed.floatValue());
		} else if (minimumDurationAllowed == null && maximumDurationAllowed != null) {
			throw new DtoConversionException("Invalid duration constraint");
		}
		
		return exercise;
	}

}
