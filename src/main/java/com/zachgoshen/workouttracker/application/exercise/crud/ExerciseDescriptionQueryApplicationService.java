package com.zachgoshen.workouttracker.application.exercise.crud;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.application.exercise.ExerciseDescriptionConverter;
import com.zachgoshen.workouttracker.application.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescriptionRepository;

@Service
public class ExerciseDescriptionQueryApplicationService {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionQueryApplicationService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	public List<ExerciseDescriptionDto> findAll() {
		return repository.findAll()
			.stream()
			.map(description -> ExerciseDescriptionConverter.toDto(description))
			.collect(Collectors.toList());
	}

}
