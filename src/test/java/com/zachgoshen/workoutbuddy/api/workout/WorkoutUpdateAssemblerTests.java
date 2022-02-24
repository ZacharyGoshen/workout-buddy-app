package com.zachgoshen.workoutbuddy.api.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.application.workout.WorkoutUpdate;

public class WorkoutUpdateAssemblerTests {
	
	@Test
	public void Assemble() {
		WorkoutDto dto = new WorkoutDto();
		dto.setName("Push Day");
		dto.setTimeCompleted(new Date());
		
		WorkoutUpdate update = WorkoutUpdateAssembler.assemble(dto);
		
		assertEquals(dto.getName(), update.getName().get());
		assertEquals(dto.getTimeCompleted(), update.getTimeCompleted().get());
	}

}
