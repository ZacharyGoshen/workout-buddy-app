package com.zachgoshen.workouttracker.domain.workout;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;

public abstract class Set {
	
	private Optional<Float> timeRested;
	private Optional<RestTimeConstraint> restTimeConstraint;
	
	protected Set() {
		timeRested = Optional.empty();
		restTimeConstraint = Optional.empty();
	}
	
	protected Set(Set set) {
		timeRested = set.getTimeRested();
		restTimeConstraint = set.getRestTimeConstraint();
	}
	
	public abstract Set clone();
	
	public Optional<Float> getTimeRested() {
		return timeRested;
	}
	
	public void setTimeRested(float timeRested) {
		this.timeRested = Optional.of(timeRested);
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
