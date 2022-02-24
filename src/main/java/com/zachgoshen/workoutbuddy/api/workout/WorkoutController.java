package com.zachgoshen.workoutbuddy.api.workout;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.set.SetConverter;
import com.zachgoshen.workoutbuddy.api.set.SetDto;
import com.zachgoshen.workoutbuddy.application.workout.NonexistentWorkoutException;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutCreationUseCase;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutDeletionUseCase;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutQueryUseCase;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutUpdate;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutUpdateUseCase;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

@RestController
@RequestMapping("/api/workouts")
public class WorkoutController {

	private final WorkoutQueryUseCase queryUseCase;
	private final WorkoutCreationUseCase creationUseCase;
	private final WorkoutUpdateUseCase updateUseCase;
	private final WorkoutDeletionUseCase deletionUseCase;
	
	public WorkoutController(
			WorkoutQueryUseCase queryUseCase, 
			WorkoutCreationUseCase creationUseCase,
			WorkoutUpdateUseCase updateUseCase, 
			WorkoutDeletionUseCase deletionUseCase) {
		
		this.queryUseCase = queryUseCase;
		this.creationUseCase = creationUseCase;
		this.updateUseCase = updateUseCase;
		this.deletionUseCase = deletionUseCase;
	}
	
	@GetMapping("")
	public List<WorkoutDto> findAll() throws DtoConversionException {
		List<Workout> workouts = queryUseCase.findAll();
		
		List<WorkoutDto> dtos = new ArrayList<>();
		
		for (Workout workout : workouts) {
			WorkoutDto dto = WorkoutConverter.toDto(workout);
			dtos.add(dto);
		}
		
		return dtos;
	}
	
	@GetMapping("/{id}")
	public WorkoutDto findById(@PathVariable("id") String id) throws DtoConversionException {
		Optional<Workout> workout = queryUseCase.findById(id);
		return WorkoutConverter.toDto(workout.get());
	}
	
	@PostMapping("")
	public String create(@RequestBody WorkoutDto dto) throws DtoConversionException, InvalidRangeException {
		Workout workout = WorkoutConverter.toEntity(dto);
		return creationUseCase.createAndReturnId(workout);
	}
	
	@PostMapping("/{id}/copy")
	public String createNotCompletedCopy(@PathVariable("id") String idOfWorkoutToCopy) throws NonexistentWorkoutException {
		return creationUseCase.createNotCompletedCopyAndReturnId(idOfWorkoutToCopy);
	}
	
	@PutMapping("/{id}")
	public void update(@PathVariable("id") String id, @RequestBody WorkoutDto workout) throws NonexistentWorkoutException {
		WorkoutUpdate update = WorkoutUpdateAssembler.assemble(workout);
		updateUseCase.update(id, update);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) {
		deletionUseCase.deleteById(id);
	}
	
	@PostMapping("/{id}/addSet/{index}")
	public void addSet(@PathVariable("id") String workoutId, @PathVariable("index") int setIndex, @RequestBody SetDto setDto) throws NonexistentWorkoutException, InvalidRangeException, DtoConversionException {
		Set set = SetConverter.toEntity(setDto);
		updateUseCase.addSet(workoutId, setIndex, set);
	}
	
	@PostMapping("/{id}/replaceSet/{index}")
	public void replaceSet(@PathVariable("id") String workoutId, @PathVariable("index") int setIndex, @RequestBody SetDto setDto) throws NonexistentWorkoutException, InvalidRangeException, DtoConversionException {
		Set set = SetConverter.toEntity(setDto);
		updateUseCase.replaceSet(workoutId, setIndex, set);
	}
	
	@PostMapping("/{id}/moveSet/{originalIndex}/{destinationIndex}")
	public void moveSet(@PathVariable("id") String workoutId, @PathVariable("originalIndex") int originalIndex, @PathVariable("destinationIndex") int destinationIndex) throws NonexistentWorkoutException {
		updateUseCase.moveSet(workoutId, originalIndex, destinationIndex);
	}
	
	@PostMapping("/{id}/removeSet/{index}")
	public void removeSet(@PathVariable("id") String workoutId, @PathVariable("index") int setIndex) throws NonexistentWorkoutException {
		updateUseCase.removeSet(workoutId, setIndex);
	}

}
