package com.zachgoshen.workouttracker.domain.workout;

import java.util.Optional;

import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;

public class Exercise {
	
	private final ExerciseDescription description;
	
	private Optional<Float> weightUsed;
	private Optional<WeightConstraint> weightConstraint;
	
	private Optional<Integer> repsCompleted;
	private Optional<RepsConstraint> repsConstraint;
	
	private Optional<Float> timePerformed;
	private Optional<DurationConstraint> durationConstraint;
	
	public Exercise(ExerciseDescription exerciseDescription) {
		description = exerciseDescription;

		weightUsed = Optional.empty();
		weightConstraint = Optional.empty();
		
		repsCompleted = Optional.empty();
		repsConstraint = Optional.empty();
		
		timePerformed = Optional.empty();
		durationConstraint = Optional.empty();
	}
	
	public Exercise(Exercise exercise) {
		description = exercise.getDescription();
		
		weightUsed = exercise.getWeightUsed();
		weightConstraint = exercise.getWeightConstraint();
		
		repsCompleted = exercise.getRepsCompleted();
		repsConstraint = exercise.getRepsConstraint();
		
		timePerformed = exercise.getTimePerformed();
		durationConstraint = exercise.getDurationConstraint();
	}
	
	public ExerciseDescription getDescription() {
		return description;
	}
	
	public Optional<Float> getWeightUsed() {
		return weightUsed;
	}
	
	public void setWeightUsed(float weightUsed) {
		this.weightUsed = Optional.of(weightUsed);
	}
	
	public Optional<Float> getMinimumWeightAllowed() {
		if (!weightConstraint.isPresent()) {
			return Optional.empty();
		} else {
			return weightConstraint.get().getMinimumWeightAllowed();
		}
	}
	
	public Optional<Float> getMaximumWeightAllowed() {
		if (!weightConstraint.isPresent()) {
			return Optional.empty();
		} else {
			return weightConstraint.get().getMaximumWeightAllowed();
		}
	}
	
	private Optional<WeightConstraint> getWeightConstraint() {
		return weightConstraint;
	}
	
	public void addMinimumWeightConstraint(float weight) throws InvalidRangeException {
		WeightConstraint constraint = new WeightConstraint(weight);
		
		weightConstraint = Optional.of(constraint);
	}
	
	public void addBoundedWeightConstraint(float minimumWeightAllowed, float maximumWeightAllowed) throws InvalidRangeException {
		WeightConstraint constraint = new WeightConstraint(minimumWeightAllowed, maximumWeightAllowed);
		
		weightConstraint = Optional.of(constraint);
	}
	
	public boolean wasWeightConstraintSatisfied() {
		if (!weightConstraint.isPresent()) {
			return true;
		} else {
			return weightConstraint.get().isSatisfiedBy(this);
		}
	}

	public Optional<Integer> getRepsCompleted() {
		return repsCompleted;
	}

	public void setRepsCompleted(int repsCompleted) {
		this.repsCompleted = Optional.of(repsCompleted);
	}
	
	public Optional<Integer> getMinimumRepsAllowed() {
		if (!repsConstraint.isPresent()) {
			return Optional.empty();
		} else {
			return repsConstraint.get().getMinimumRepsAllowed();
		}
	}
	
	public Optional<Integer> getMaximumRepsAllowed() {
		if (!repsConstraint.isPresent()) {
			return Optional.empty();
		} else {
			return repsConstraint.get().getMaximumRepsAllowed();
		}
	}
	
	private Optional<RepsConstraint> getRepsConstraint() {
		return repsConstraint;
	}
	
	public void addMinimumRepsConstraint(int reps) throws InvalidRangeException {
		RepsConstraint constraint = new RepsConstraint(reps);
		
		repsConstraint = Optional.of(constraint);
	}
	
	public void addBoundedRepsConstraint(int minimumRepsAllowed, int maximumRepsAllowed) throws InvalidRangeException {
		RepsConstraint constraint = new RepsConstraint(minimumRepsAllowed, maximumRepsAllowed);
		
		repsConstraint = Optional.of(constraint);
	}
	
	public boolean wasRepsConstraintSatisfied() {
		if (!repsConstraint.isPresent()) {
			return true;
		} else {
			return repsConstraint.get().isSatisfiedBy(this);
		}
	}

	public Optional<Float> getTimePerformed() {
		return timePerformed;
	}

	public void setTimePerformed(float timePerformed) {
		this.timePerformed = Optional.of(timePerformed);
	}
	
	private Optional<DurationConstraint> getDurationConstraint() {
		return durationConstraint;
	}
	
	public Optional<Float> getMinimumDurationAllowed() {
		if (!durationConstraint.isPresent()) {
			return Optional.empty();
		} else {
			return durationConstraint.get().getMinimumDurationAllowed();
		}
	}
	
	public Optional<Float> getMaximumDurationAllowed() {
		if (!durationConstraint.isPresent()) {
			return Optional.empty();
		} else {
			return durationConstraint.get().getMaximumDurationAllowed();
		}
	}
	
	public void addMinimumDurationConstraint(float duration) throws InvalidRangeException {
		DurationConstraint constraint = new DurationConstraint(duration);
		
		durationConstraint = Optional.of(constraint);
	}
	
	public void addBoundedDurationConstraint(float minimumDurationAllowed, float maximumDurationAllowed) throws InvalidRangeException {
		DurationConstraint constraint = new DurationConstraint(minimumDurationAllowed, maximumDurationAllowed);
		
		durationConstraint = Optional.of(constraint);
	}
	
	public boolean wasDurationConstraintSatisfied() {
		if (!durationConstraint.isPresent()) {
			return true;
		} else {
			return durationConstraint.get().isSatisfiedBy(this);
		}
	}
	
	public boolean wereConstraintsSatisfied() {
		return wasWeightConstraintSatisfied() && wasRepsConstraintSatisfied() && wasDurationConstraintSatisfied();
	}

}
