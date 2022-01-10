package com.zachgoshen.workouttracker.application.exercise.crud;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.exercise.ExerciseDescriptionConverter;
import com.zachgoshen.workouttracker.application.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescriptionRepository;

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
