package com.zachgoshen.workoutbuddy.application.workout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.set.SetConverter;
import com.zachgoshen.workoutbuddy.api.set.SetDto;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class WorkoutConverter {
	
	public static WorkoutDto toDto(Workout workout) throws DtoConversionException {
		WorkoutDto dto = new WorkoutDto();
		
		dto.setId(workout.getId());
		
		Optional<String> name = workout.getName();
		if (name.isPresent()) {
			dto.setName(name.get());
		}
		
		Optional<Date> timeCompleted = workout.getTimeCompleted();
		if (timeCompleted.isPresent()) {
			dto.setTimeCompleted(timeCompleted.get());
		}
		
		List<SetDto> setDtos = new ArrayList<>();
		
		for (Set set: workout.getSets()) {
			SetDto setDto = SetConverter.toDto(set);
			setDtos.add(setDto);
		}
		
		dto.setSets(setDtos);
		
		return dto;
	}
	
	public static Workout toEntity(WorkoutDto workoutDto) throws DtoConversionException, InvalidRangeException {
		Workout workout = new Workout();
		
		String name = workoutDto.getName();
		workout.setName(name);
		
		Date timeCompleted = workoutDto.getTimeCompleted();
		workout.setTimeCompleted(timeCompleted);
		
		for (SetDto setDto : workoutDto.getSets()) {
			Set set = SetConverter.toEntity(setDto);
			workout.appendSet(set);
		}
		
		return workout;
	}

}
