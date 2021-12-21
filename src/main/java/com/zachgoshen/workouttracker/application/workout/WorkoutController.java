package com.zachgoshen.workouttracker.application.workout;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.NonexistentWorkoutException;
import com.zachgoshen.workouttracker.application.set.SetDto;
import com.zachgoshen.workouttracker.domain.common.math.InvalidRangeException;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

	private final WorkoutQueryApplicationService queryService;
	private final WorkoutUpdateApplicationService updateService;
	
	public WorkoutController(WorkoutQueryApplicationService queryService, WorkoutUpdateApplicationService updateService) {
		this.queryService = queryService;
		this.updateService = updateService;
	}
	
	@GetMapping("")
	public List<WorkoutDto> findAll() {
		return queryService.findAll();
	}
	
	@GetMapping("/{id}")
	public WorkoutDto findById(@PathVariable("id") String id) {
		return queryService.findById(id);
	}
	
	@PostMapping("/{id}/addSet")
	public void addSet(@PathVariable("id") String workoutId, @RequestBody SetDto set) throws NonexistentWorkoutException, InvalidRangeException, DtoConversionException {
		updateService.addSet(workoutId, set);
	}
	
	@PostMapping("/{id}/updateSet/{index}")
	public void updateSet(@PathVariable("id") String workoutId, @PathVariable("index") int setIndex, @RequestBody SetDto updatedSet) throws NonexistentWorkoutException, InvalidRangeException, DtoConversionException {
		updateService.updateSet(workoutId, setIndex, updatedSet);
	}
	
	@PostMapping("/{id}/moveSet/{originalIndex}/{destinationIndex}")
	public void moveSet(@PathVariable("id") String workoutId, @PathVariable("originalIndex") int originalIndex, @PathVariable("destinationIndex") int destinationIndex) throws NonexistentWorkoutException {
		updateService.moveSet(workoutId, originalIndex, destinationIndex);
	}
	
	@PostMapping("/{id}/removeSet/{index}")
	public void removeSet(@PathVariable("id") String workoutId, @PathVariable("index") int setIndex) throws NonexistentWorkoutException {
		updateService.removeSet(workoutId, setIndex);
	}

}
