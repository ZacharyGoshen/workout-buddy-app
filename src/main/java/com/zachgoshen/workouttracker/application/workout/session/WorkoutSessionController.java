package com.zachgoshen.workouttracker.application.workout.session;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/workout-sessions")
public class WorkoutSessionController {
	
	private final WorkoutSessionApplicationService workoutSessionService;
	
	public WorkoutSessionController(WorkoutSessionApplicationService workoutSessionService) {
		this.workoutSessionService = workoutSessionService;
	}
	
	@GetMapping("")
	public List<WorkoutSessionDto> findAll() {
		return workoutSessionService.findAll();
	}
	
	@GetMapping("/{id}")
	public WorkoutSessionDto findById(@PathVariable long id) {
		return workoutSessionService.findById(id);
	}
	
}
