package com.zachgoshen.workoutbuddy.api.workout;

import java.util.Date;

import com.zachgoshen.workoutbuddy.application.workout.WorkoutUpdate;

public final class WorkoutUpdateAssembler {
	
	private WorkoutUpdateAssembler() {}
	
	public static WorkoutUpdate assemble(WorkoutDto dto) {
		WorkoutUpdate update = new WorkoutUpdate();
		
		String name = dto.getName();
		update.setName(name);
		
		Date timeCompleted = dto.getTimeCompleted();
		update.setTimeCompleted(timeCompleted);
		
		return update;
	}

}
