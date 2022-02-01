package com.zachgoshen.workoutbuddy.data.set;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.SetRepository;
import com.zachgoshen.workoutbuddy.domain.set.SetSortOrder;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

@Repository
public class MockSetRepository implements SetRepository {
	
	private final WorkoutRepository workoutRepository;

	public MockSetRepository(WorkoutRepository workoutRepository) {
		this.workoutRepository = workoutRepository;
	}
	
	@Override
	public List<Set> findAll() {
		List<Workout> allWorkouts = workoutRepository.findAll();
		
		return allWorkouts.stream()
			.flatMap(workout -> workout.getSets().stream())
			.collect(Collectors.toList());
	}

	@Override
	public List<Set> findBy(Specification<Set> specification) {
		List<Set> allSets = findAll();
		
		return allSets.stream()
			.filter(set -> specification.isSatisfiedBy(set))
			.collect(Collectors.toList());
	}

	@Override
	public List<Set> findSortedBy(Specification<Set> specification, SetSortOrder order) {
		List<Set> sets = findBy(specification);
		
		if (order == SetSortOrder.LEAST_RECENT_COMPLETION_TIME) {
			sets.sort(buildLeastRecentCompletionTimeComparator());
		} else if (order == SetSortOrder.MOST_RECENT_COMPLETION_TIME) {
			sets.sort(buildMostRecentCompletionTimeComparator());
		}
		
		return sets;
	}
	
	private static Comparator<Set> buildLeastRecentCompletionTimeComparator() {
		return new Comparator<Set>() {
		    public int compare(Set set1, Set set2) {
		    	Optional<Date> timeCompleted1 = set1.getTimeCompleted();
		    	Optional<Date> timeCompleted2 = set2.getTimeCompleted();
		    	
		    	if (timeCompleted1.isPresent() && timeCompleted2.isPresent()) {
		    		return timeCompleted1.get().compareTo(timeCompleted2.get());
		    	} else if (timeCompleted1.isPresent()) {
		    		return -1;
		    	} else if (timeCompleted2.isPresent()) {
		    		return 1;
		    	} else {
		    		return 0;
		    	}
		    }
		};
	}
	
	private static Comparator<Set> buildMostRecentCompletionTimeComparator() {
		return new Comparator<Set>() {
		    public int compare(Set set1, Set set2) {
		    	Optional<Date> timeCompleted1 = set1.getTimeCompleted();
		    	Optional<Date> timeCompleted2 = set2.getTimeCompleted();
		    	
		    	if (timeCompleted1.isPresent() && timeCompleted2.isPresent()) {
		    		return timeCompleted2.get().compareTo(timeCompleted1.get());
		    	} else if (timeCompleted1.isPresent()) {
		    		return -1;
		    	} else if (timeCompleted2.isPresent()) {
		    		return 1;
		    	} else {
		    		return 0;
		    	}
		    }
		};
	}

}
