package com.zachgoshen.workouttracker.application.workout.plan;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController()
@RequestMapping("/api/workout-plans")
public class WorkoutPlanController {
	
	private final WorkoutPlanApplicationService workoutPlanService;
	
	public WorkoutPlanController(WorkoutPlanApplicationService workoutPlanService) {
		this.workoutPlanService = workoutPlanService;
	}
	
	@GetMapping("")
	public List<WorkoutPlanDto> findAll() {
		return workoutPlanService.findAll();
	}
	
	@GetMapping("/{id}")
	public WorkoutPlanDto findById(@PathVariable long id) {
		return workoutPlanService.findById(id);
	}
	
}
