package com.zachgoshen.workoutbuddy.api.set;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseConverter;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDto;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.Superset;

public final class SetConverter {
	
	private SetConverter() {}
	
	public static SetDto toDto(Set set) throws DtoConversionException {
		SetDto setDto = assembleDtoWithoutExercises(set);

		String type = getSetType(set);
		setDto.setType(type);
		
		List<ExerciseDto> exerciseDtos = assembleExerciseDtos(set);
		setDto.setExercises(exerciseDtos);
		
		return setDto;
	}
	
	private static SetDto assembleDtoWithoutExercises(Set set) {
		SetDto dto = new SetDto();
		
		Optional<Date> timeCompleted = set.getTimeCompleted();
		if (timeCompleted.isPresent()) {
			dto.setTimeCompleted(timeCompleted.get());
		}
		
		Optional<Float> timeRested = set.getTimeRested();
		if (timeRested.isPresent()) {
			dto.setTimeRested(timeRested.get());
		}
		
		Optional<Float> minimumRestTimeAllowed = set.getMinimumRestTimeAllowed();
		if (minimumRestTimeAllowed.isPresent()) {
			dto.setMinimumRestTimeAllowed(minimumRestTimeAllowed.get());
		}
		
		Optional<Float> maximumRestTimeAllowed = set.getMaximumRestTimeAllowed();
		if (maximumRestTimeAllowed.isPresent()) {
			dto.setMaximumRestTimeAllowed(maximumRestTimeAllowed.get());
		}
		
		return dto;
	}
	
	private static String getSetType(Set set) throws DtoConversionException {
		if (set instanceof SingleExerciseSet) {
			return "Single Exercise Set";
		} else if (set instanceof Superset) {
			return "Superset";
		} else {
			throw new DtoConversionException("Set can't be converted");
		}
	}
	
	private static List<ExerciseDto> assembleExerciseDtos(Set set) {
		List<ExerciseDto> exerciseDtos = new ArrayList<>();
		
		if (set instanceof SingleExerciseSet) {
			SingleExerciseSet singleExerciseSet = (SingleExerciseSet) set;
			
			ExerciseDto exerciseDto = ExerciseConverter.toDto(singleExerciseSet.getExercise());
			exerciseDtos = Arrays.asList(exerciseDto);
		} else if (set instanceof Superset) {
			Superset superset = (Superset) set;
			
			exerciseDtos = superset.getExercises()
				.stream()
				.map(exercise -> ExerciseConverter.toDto(exercise))
				.collect(Collectors.toList());
		}
		
		return exerciseDtos;
	}
	
	public static Set toEntity(SetDto dto) throws InvalidRangeException, DtoConversionException {
		String type = dto.getType();
		Set set;
		
		if (type.equals("Single Exercise Set")) {
			set = assembleSingleExerciseSetWithExerciseOnly(dto);
		} else if (type.equals("Superset")) {
			set = assembleSupersetWithExercisesOnly(dto);
		} else {
			String message = String.format("'%s' is not a valid set type", type);
			throw new DtoConversionException(message);
		}
		
		set.setTimeCompleted(dto.getTimeCompleted());
		
		set.setTimeRested(dto.getTimeRested());
		
		Float minimumRestTimeAllowed = dto.getMinimumRestTimeAllowed();
		Float maximumRestTimeAllowed = dto.getMaximumRestTimeAllowed();
		
		if (minimumRestTimeAllowed != null && maximumRestTimeAllowed != null) {
			set.addBoundedRestTimeConstraint(minimumRestTimeAllowed.floatValue(), maximumRestTimeAllowed.floatValue());
		} else if (minimumRestTimeAllowed == null && maximumRestTimeAllowed != null) {
			set.addMaximumRestTimeConstraint(maximumRestTimeAllowed.floatValue());
		} else if (minimumRestTimeAllowed != null && maximumRestTimeAllowed == null) {
			throw new DtoConversionException("Invalid rest time constraint");
		}
		
		return set;
	}
	
	public static SingleExerciseSet assembleSingleExerciseSetWithExerciseOnly(SetDto dto) throws InvalidRangeException, DtoConversionException {
		ExerciseDto exerciseDto = dto.getExercises().get(0);
		Exercise exercise = ExerciseConverter.toEntity(exerciseDto);
		return new SingleExerciseSet(exercise);
	}
	
	public static Superset assembleSupersetWithExercisesOnly(SetDto dto) throws InvalidRangeException, DtoConversionException {
		List<Exercise> exercises = new ArrayList<>();
		
		for (ExerciseDto exerciseDto : dto.getExercises()) {
			Exercise exercise = ExerciseConverter.toEntity(exerciseDto);
			exercises.add(exercise);
		}
		
		return new Superset(exercises);
	}

}
