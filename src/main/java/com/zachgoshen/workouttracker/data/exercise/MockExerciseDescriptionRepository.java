package com.zachgoshen.workouttracker.data.exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.exercise.ExerciseDescriptionRepository;
import com.zachgoshen.workouttracker.domain.exercise.MuscleGroup;

@Repository
public class MockExerciseDescriptionRepository implements ExerciseDescriptionRepository {
	
	private final List<ExerciseDescription> descriptions;

	public MockExerciseDescriptionRepository() {
		this.descriptions = buildDescriptions();
	}
	
	private static List<ExerciseDescription> buildDescriptions() {
		List<ExerciseDescription> descriptions = new ArrayList<>();
		
		ExerciseDescription sumoDeadliftDescription = new ExerciseDescription("Sumo Deadlift");
		sumoDeadliftDescription.addMuscleGroup(MuscleGroup.GLUTES);
		sumoDeadliftDescription.addMuscleGroup(MuscleGroup.HAMSTRINGS);
		sumoDeadliftDescription.addMuscleGroup(MuscleGroup.LATS);
		sumoDeadliftDescription.addMuscleGroup(MuscleGroup.LOWER_BACK);
		descriptions.add(sumoDeadliftDescription);
		
		ExerciseDescription hamstringCurlDescription = new ExerciseDescription("Hamstring Curl");
		hamstringCurlDescription.addMuscleGroup(MuscleGroup.BICEPS);
		descriptions.add(hamstringCurlDescription);
		
		ExerciseDescription latPulldownDescription = new ExerciseDescription("Lat Pulldown");
		latPulldownDescription.addMuscleGroup(MuscleGroup.BICEPS);
		latPulldownDescription.addMuscleGroup(MuscleGroup.LATS);
		descriptions.add(latPulldownDescription);
		
		ExerciseDescription pullupDescription = new ExerciseDescription("Pullup");
		pullupDescription.addMuscleGroup(MuscleGroup.BICEPS);
		pullupDescription.addMuscleGroup(MuscleGroup.LATS);
		descriptions.add(pullupDescription);
		
		ExerciseDescription chestSupportedRowDescription = new ExerciseDescription("Chest Supported Row");
		chestSupportedRowDescription.addMuscleGroup(MuscleGroup.BICEPS);
		chestSupportedRowDescription.addMuscleGroup(MuscleGroup.LATS);
		descriptions.add(chestSupportedRowDescription);
		
		ExerciseDescription ezBarCurlDescription = new ExerciseDescription("EZ Bar Curl");
		ezBarCurlDescription.addMuscleGroup(MuscleGroup.BICEPS);
		descriptions.add(ezBarCurlDescription);
		
		ExerciseDescription dumbbellCurlDescription = new ExerciseDescription("Dumbbell Curl");
		dumbbellCurlDescription.addMuscleGroup(MuscleGroup.BICEPS);
		descriptions.add(dumbbellCurlDescription);
		
		return descriptions;
	}

	@Override
	public List<ExerciseDescription> findAll() {
		descriptions.sort(buildAlphabeticalNameComparator());
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

	@Override
	public boolean existsById(String id) {
		Optional<ExerciseDescription> description = findById(id);
		
		return description.isPresent();
	}
	
	private static Comparator<ExerciseDescription> buildAlphabeticalNameComparator() {
		return new Comparator<ExerciseDescription>() {
		    public int compare(ExerciseDescription decription1, ExerciseDescription decription2) {
		    	String name1 = decription1.getName();
		    	String name2 = decription2.getName();
		    	
		    	return name1.compareTo(name2);
		    }
		};
	}

}
