package com.zachgoshen.workouttracker.application.exercise;

import java.util.Optional;

import org.springframework.stereotype.Service;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescriptionRepository;

@Service
public class ExerciseDescriptionUpdateApplicationService {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionUpdateApplicationService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	public void update(ExerciseDescriptionDto dto) throws NonexistentExerciseDescriptionException {
		String id = dto.getId();
		ExerciseDescription description = tryToFindExerciseDescriptionById(id);
		
		String name = dto.getName();
		description.setName(name);
		
		repository.save(description);
	}
	
	private ExerciseDescription tryToFindExerciseDescriptionById(String id) throws NonexistentExerciseDescriptionException {
		Optional<ExerciseDescription> description = repository.findById(id);
		
		if (description.isPresent()) {
			return description.get();
		} else {
			throw new NonexistentExerciseDescriptionException();
		}
	}

}
