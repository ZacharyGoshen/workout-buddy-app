package com.zachgoshen.workouttracker.application.workout;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zachgoshen.workouttracker.domain.workout.Workout;
import com.zachgoshen.workouttracker.domain.workout.WorkoutRepository;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {
	
	private final WorkoutRepository repository;
	
	public WorkoutController(WorkoutRepository repository) {
		this.repository = repository;
	}
	
	@GetMapping("")
	public List<WorkoutDto> findAll() {
		return repository.findAll()
			.stream()
			.map(workout -> WorkoutDtoAssembler.assemble(workout))
			.collect(Collectors.toList());
	}
	
	@GetMapping("/{id}")
	public WorkoutDto findById(@PathVariable("id") String id) {
		Optional<Workout> workout = repository.findById(id);
		
		return WorkoutDtoAssembler.assemble(workout.get());
	}

}
