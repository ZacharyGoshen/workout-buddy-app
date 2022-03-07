package com.zachgoshen.workoutbuddy.data.exercise;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionSortOrder;

public class MockExerciseDescriptionRepository implements ExerciseDescriptionRepository {
	
	private final List<ExerciseDescription> descriptions;

	public MockExerciseDescriptionRepository() {
		descriptions = new ArrayList<>();
	}

	@Override
	public List<ExerciseDescription> findAll() {
		return descriptions;
	}

	@Override
	public List<ExerciseDescription> findSortedBy(Specification<ExerciseDescription> specification, ExerciseDescriptionSortOrder sortOrder) {
		List<ExerciseDescription> satisfyingDescriptions = descriptions.stream()
			.filter(description -> specification.isSatisfiedBy(description))
			.collect(Collectors.toList());
		
		if (sortOrder.equals(ExerciseDescriptionSortOrder.NAME_ALPHABETICALLY)) {
			satisfyingDescriptions.sort(buildAlphabeticalNameComparator());
		} else {
			satisfyingDescriptions.sort(buildReverseAlphabeticalNameComparator());
		}
		
		return satisfyingDescriptions;
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
	public void deleteAll() {
		descriptions.removeAll(descriptions);
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
	
	private static Comparator<ExerciseDescription> buildReverseAlphabeticalNameComparator() {
		return new Comparator<ExerciseDescription>() {
		    public int compare(ExerciseDescription decription1, ExerciseDescription decription2) {
		    	String name1 = decription1.getName();
		    	String name2 = decription2.getName();
		    	
		    	return name2.compareTo(name1);
		    }
		};
	}

}
