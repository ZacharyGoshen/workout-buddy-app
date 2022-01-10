package com.zachgoshen.workouttracker.data.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescriptionRepository;

@Repository
public class MockExerciseDescriptionRepository implements ExerciseDescriptionRepository {
	
	private final List<ExerciseDescription> descriptions;

	public MockExerciseDescriptionRepository() {
		this.descriptions = buildDescriptions();
	}
	
	private static List<ExerciseDescription> buildDescriptions() {
		List<ExerciseDescription> descriptions = new ArrayList<>();
		
		ExerciseDescription sumoDeadliftDescription = new ExerciseDescription("Sumo Deadlift");
		descriptions.add(sumoDeadliftDescription);
		
		ExerciseDescription hamstringCurlDescription = new ExerciseDescription("Hamstring Curl");
		descriptions.add(hamstringCurlDescription);
		
		ExerciseDescription latPulldownDescription = new ExerciseDescription("Lat Pulldown");
		descriptions.add(latPulldownDescription);
		
		ExerciseDescription pullupDescription = new ExerciseDescription("Pullup");
		descriptions.add(pullupDescription);
		
		ExerciseDescription chestSupportedRowDescription = new ExerciseDescription("Chest Supported Row");
		descriptions.add(chestSupportedRowDescription);
		
		ExerciseDescription ezBarCurlDescription = new ExerciseDescription("EZ Bar Curl");
		descriptions.add(ezBarCurlDescription);
		
		ExerciseDescription dumbbellCurlDescription = new ExerciseDescription("Dumbbell Curl");
		descriptions.add(dumbbellCurlDescription);
		
		return descriptions;
	}

	@Override
	public List<ExerciseDescription> findAll() {
		return descriptions;
	}

	@Override
	public Optional<ExerciseDescription> findById(String id) {
		return descriptions.stream()
			.filter(description -> description.getId().equals(id))
			.findFirst();
	}

	@Override
	public void save(ExerciseDescription description) {
		if (!descriptions.contains(description)) {
			descriptions.add(description);
		}
	}

	@Override
	public void deleteById(String id) {
		Optional<ExerciseDescription> description = findById(id);
		
		if (description.isPresent()) {
			descriptions.remove(description.get());
		}
	}

}
