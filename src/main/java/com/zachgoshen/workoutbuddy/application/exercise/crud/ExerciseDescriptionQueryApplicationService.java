package com.zachgoshen.workoutbuddy.application.exercise.crud;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionConverter;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescriptionRepository;

@Service
public class ExerciseDescriptionQueryApplicationService {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionQueryApplicationService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	public List<ExerciseDescriptionDto> findAll() {
		List<ExerciseDescriptionDto> dtos = new ArrayList<>();
		
		List<ExerciseDescription> descriptions = repository.findAll();
		
		for (ExerciseDescription description : descriptions) {
			ExerciseDescriptionDto dto = ExerciseDescriptionConverter.toDto(description);
			dtos.add(dto);
		}
		
		return dtos;
	}

}
