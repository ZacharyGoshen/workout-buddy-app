package com.zachgoshen.workouttracker.domain.set;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;

public abstract class Set {

	private final String id;
	private Optional<Date> timeCompleted;
	private Optional<Float> timeRested;
	private Optional<RestTimeConstraint> restTimeConstraint;
	
	protected Set() {
		this(Optional.empty(), Optional.empty(), Optional.empty());
	}
	
	protected Set(Set set) {
		this(set.getTimeCompleted(), set.getTimeRested(), set.getRestTimeConstraint());
	}
	
	private Set(Optional<Date> timeCompleted, Optional<Float> timeRested, Optional<RestTimeConstraint> restTimeConstraint) {
		id = UUID.randomUUID().toString();
		this.timeCompleted = timeCompleted;
		this.timeRested = timeRested;
		this.restTimeConstraint = restTimeConstraint;
	}
	
	public abstract Set clone();
	
	public String getId() {
		return id;
	}
	
	public Optional<Date> getTimeCompleted() {
		return timeCompleted;
	}

	public void setTimeCompleted(Date timeCompleted) {
		this.timeCompleted = Optional.ofNullable(timeCompleted);
	}

	public Optional<Float> getTimeRested() {
		return timeRested;
	}
	
	public void setTimeRested(Float timeRested) {
		this.timeRested = Optional.ofNullable(timeRested);
	}
	
	private Optional<RestTimeConstraint> getRestTimeConstraint() {
		return restTimeConstraint;
	}
	
	public Optional<Float> getMinimumRestTimeAllowed() {
		if (!restTimeConstraint.isPresent()) {
			return Optional.empty();
		} else {
			return restTimeConstraint.get().getMinimumRestTimeAllowed();
		}
	}
	
	public Optional<Float> getMaximumRestTimeAllowed() {
		if (!restTimeConstraint.isPresent()) {
			return Optional.empty();
		} else {
			return restTimeConstraint.get().getMaximumRestTimeAllowed();
		}
	}
	
	public void addMaximumRestTimeConstraint(float time) throws InvalidRangeException {
		RestTimeConstraint constraint = new RestTimeConstraint(time);
		
		restTimeConstraint = Optional.of(constraint);
	}
	
	public void addBoundedRestTimeConstraint(float minimumRestTimeAllowed, float maximumRestTimeAllowed) throws InvalidRangeException {
		RestTimeConstraint constraint = new RestTimeConstraint(minimumRestTimeAllowed, maximumRestTimeAllowed);
		
		restTimeConstraint = Optional.of(constraint);
	}
	
	public abstract boolean wereConstraintsSatisfied();
	
	public boolean wasRestTimeConstraintSatisfied() {
		if (!restTimeConstraint.isPresent()) {
			return true;
		} else {
			return restTimeConstraint.get().isSatisfiedBy(this);
		}
	}

}
