package com.zachgoshen.workouttracker.application.workout;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zachgoshen.workouttracker.application.set.SetDto;
import com.zachgoshen.workouttracker.application.set.SetConverter;
import com.zachgoshen.workouttracker.domain.workout.Workout;

public class WorkoutConverter {
	
	public static WorkoutDto toDto(Workout workout) {
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
		
		List<SetDto> sets = workout.getSets()
			.stream()
			.map(set -> SetConverter.toDto(set))
			.collect(Collectors.toList());
		dto.setSets(sets);
		
		return dto;
	}
	
	public static Workout toEntity(WorkoutDto dto) {
		return null;
	}

}
