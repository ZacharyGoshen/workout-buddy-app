package com.zachgoshen.workoutbuddy.application.workout;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.set.SetDto;
import com.zachgoshen.workoutbuddy.application.workout.crud.WorkoutCreationApplicationService;
import com.zachgoshen.workoutbuddy.application.workout.crud.WorkoutDeletionApplicationService;
import com.zachgoshen.workoutbuddy.application.workout.crud.WorkoutQueryApplicationService;
import com.zachgoshen.workoutbuddy.application.workout.crud.WorkoutUpdateApplicationService;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

	private final WorkoutQueryApplicationService queryService;
	private final WorkoutCreationApplicationService creationService;
	private final WorkoutUpdateApplicationService updateService;
	private final WorkoutDeletionApplicationService deletionService;
	
	public WorkoutController(
			WorkoutQueryApplicationService queryService, 
			WorkoutCreationApplicationService creationService,
			WorkoutUpdateApplicationService updateService, 
			WorkoutDeletionApplicationService deletionService) {
		
		this.queryService = queryService;
		this.creationService = creationService;
		this.updateService = updateService;
		this.deletionService = deletionService;
	}
	
	@GetMapping("")
	public List<WorkoutDto> findAll() throws DtoConversionException {
		return queryService.findAll();
	}
	
	@GetMapping("/{id}")
	public WorkoutDto findById(@PathVariable("id") String id) throws DtoConversionException {
		return queryService.findById(id);
	}
	
	@PostMapping("")
	public String create(@RequestBody WorkoutDto workout) throws DtoConversionException, InvalidRangeException {
		return creationService.createAndReturnId(workout);
	}
	
	@PostMapping("/{id}/copy")
	public String createNotCompletedCopy(@PathVariable("id") String idOfWorkoutToCopy) throws NonexistentWorkoutException {
		return creationService.createNotCompletedCopyAndReturnId(idOfWorkoutToCopy);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") String id, @RequestBody WorkoutDto workout) throws NonexistentWorkoutException {
		updateService.update(id, workout);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		deletionService.deleteById(id);
	}
	
	@PostMapping("/{id}/addSet/{index}")
	public void addSet(@PathVariable("id") String workoutId, @PathVariable("index") int setIndex, @RequestBody SetDto set) throws NonexistentWorkoutException, InvalidRangeException, DtoConversionException {
		updateService.addSet(workoutId, setIndex, set);
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
