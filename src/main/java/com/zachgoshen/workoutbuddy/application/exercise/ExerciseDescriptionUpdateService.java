package com.zachgoshen.workoutbuddy.application.exercise;

import java.util.List;
import java.util.Optional;

import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public class ExerciseDescriptionUpdateService implements ExerciseDescriptionUpdateUseCase {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionUpdateService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	@Override
	public void updateById(String id, ExerciseDescriptionUpdate update) throws NonexistentExerciseDescriptionException {
		ExerciseDescription description = tryToFindExerciseDescriptionById(id);
		
		Optional<String> name = update.getName();
		if (name.isPresent()) {
			description.setName(name.get());
		}
		
		Optional<String> notes = update.getNotes();
		if (notes.isPresent()) {
			description.setNotes(notes.get());
		}
		
		Optional<List<MuscleGroup>> muscleGroups = update.getMuscleGroups();
		if (muscleGroups.isPresent()) {
			description.setMuscleGroups(muscleGroups.get());
		}
		
		repository.save(description);
	}
	
	private ExerciseDescription tryToFindExerciseDescriptionById(String id) throws NonexistentExerciseDescriptionException {
		Optional<ExerciseDescription> description = repository.findById(id);
		
		if (description.isPresent()) {
			return description.get();
		} else {
			throw new NonexistentExerciseDescriptionException(id);
		}
	}

}
