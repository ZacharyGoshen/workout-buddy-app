package com.zachgoshen.workouttracker.data.exercise;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zachgoshen.workouttracker.domain.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescriptionRepository;
import com.zachgoshen.workouttracker.domain.set.Set;
import com.zachgoshen.workouttracker.domain.set.SetRepository;
import com.zachgoshen.workouttracker.domain.set.SingleExerciseSet;
import com.zachgoshen.workouttracker.domain.set.Superset;

@Repository
public class MockExerciseDescriptionRepository implements ExerciseDescriptionRepository {
	
	private final SetRepository setRepository;

	public MockExerciseDescriptionRepository(SetRepository setRepository) {
		this.setRepository = setRepository;
	}

	@Override
	public List<ExerciseDescription> findAll() {
		List<Set> sets = setRepository.findAll();
		
		return sets.stream()
			.flatMap(set -> getUniqueExerciseDescriptionsFromSet(set).stream())
			.distinct()
			.collect(Collectors.toList());
	}
	
	private static List<ExerciseDescription> getUniqueExerciseDescriptionsFromSet(Set set) {
		List<Exercise> exercises = new ArrayList<>();
		
		if (set instanceof SingleExerciseSet) {
			SingleExerciseSet singleExerciseSet = (SingleExerciseSet) set;
			exercises = Arrays.asList(singleExerciseSet.getExercise());
		} else if (set instanceof Superset) {
			Superset superset = (Superset) set;
			exercises = superset.getExercises();
		}

		List<ExerciseDescription> descriptions = new ArrayList<>();
		
		for (Exercise exercise : exercises) {
			ExerciseDescription description = exercise.getDescription();
			if (!descriptions.contains(description)) {
				descriptions.add(description);
			}
		}
		
		return descriptions;
	}

}
