package com.zachgoshen.workoutbuddy.application.exercise.crud;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.zachgoshen.workoutbuddy.application.DtoConversionException;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workoutbuddy.application.exercise.MuscleGroupConverter;
import com.zachgoshen.workoutbuddy.application.exercise.NonexistentExerciseDescriptionException;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.exercise.MuscleGroup;

@Service
public class ExerciseDescriptionUpdateApplicationService {
	
	private final ExerciseDescriptionRepository repository;
	
	public ExerciseDescriptionUpdateApplicationService(ExerciseDescriptionRepository repository) {
		this.repository = repository;
	}
	
	public void update(String id, ExerciseDescriptionDto dto) throws DtoConversionException, NonexistentExerciseDescriptionException {
		ExerciseDescription description = tryToFindExerciseDescriptionById(id);
		
		String name = dto.getName();
		description.setName(name);
		
		List<MuscleGroup> muscleGroups = new ArrayList<>();
		List<String> muscleGroupStrings = dto.getMuscleGroups();
		
		for (String muscleGroupString : muscleGroupStrings) {
			MuscleGroup muscleGroup = MuscleGroupConverter.toEnum(muscleGroupString);
			muscleGroups.add(muscleGroup);
		}
		
		description.setMuscleGroups(muscleGroups);
		
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
