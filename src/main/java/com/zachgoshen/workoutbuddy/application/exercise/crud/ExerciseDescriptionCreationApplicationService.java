package com.zachgoshen.workoutbuddy.application.exercise.crud;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.application.DtoConversionException;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionConverter;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescriptionRepository;

@Service
public class ExerciseDescriptionCreationApplicationService {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionCreationApplicationService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	public void create(ExerciseDescriptionDto dto) throws DtoConversionException {
		ExerciseDescription description = ExerciseDescriptionConverter.toEntity(dto);
		repository.save(description);
	}

}
