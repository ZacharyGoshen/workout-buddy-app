package com.zachgoshen.workoutbuddy.domain.workout;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import com.zachgoshen.workoutbuddy.domain.set.Set;

public class Workout {
	
	private final String id;
	private Optional<String> name;
	private Optional<Date> timeCompleted;
	private List<Set> sets;
	
	public Workout() {
		id = UUID.randomUUID().toString();
		name = Optional.empty();
		timeCompleted = Optional.empty();
		sets = new ArrayList<>();
	}
	
	public Workout(Workout workout) {
		id = UUID.randomUUID().toString();
		name = workout.getName();
		timeCompleted = workout.getTimeCompleted();
		
		sets = workout.getSets()
			.stream()
			.map(set -> set.clone())
			.collect(Collectors.toList());
	}

	public String getId() {
		return id;
	}

	public Optional<String> getName() {
		return name;
	}

	public void setName(String name) {
		this.name = Optional.ofNullable(name);
	}

	public Optional<Date> getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = Optional.ofNullable(timeCompleted);
	}

	public List<Set> getSets() {
		return sets;
	}
	
	public void addSet(Set set, int index) {
		sets.add(index, set);
	}
	
	public void appendSet(Set set) {
		sets.add(set);
	}
	
	public void duplicateSet(int index) {
		Set setToCopy = sets.get(index);
		Set copy = setToCopy.clone();
		addSet(copy, index + 1);
	}
	
	public void duplicateLastSet() {
		int indexOfLastSet = sets.size() - 1;
		duplicateSet(indexOfLastSet);
	}
	
	public void moveSet(int originalIndex, int destinationIndex) {
		Set setBeingMoved = sets.remove(originalIndex);
		
		sets.add(destinationIndex, setBeingMoved);
	}
	
	public void removeSet(int index) {
		sets.remove(index);
	}
	
	public boolean containsSet(String setId) {
		for (Set set : sets) {
			if (set.getId().equals(setId)) {
				return true;
			}
		}
		
		return false;
	}

}
