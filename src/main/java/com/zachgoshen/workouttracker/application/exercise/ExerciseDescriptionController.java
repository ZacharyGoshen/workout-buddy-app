package com.zachgoshen.workouttracker.application.exercise;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zachgoshen.workouttracker.application.DtoConversionException;
import com.zachgoshen.workouttracker.application.exercise.crud.ExerciseDescriptionCreationApplicationService;
import com.zachgoshen.workouttracker.application.exercise.crud.ExerciseDescriptionDeletionApplicationService;
import com.zachgoshen.workouttracker.application.exercise.crud.ExerciseDescriptionQueryApplicationService;
import com.zachgoshen.workouttracker.application.exercise.crud.ExerciseDescriptionUpdateApplicationService;

@RestController
@RequestMapping("/api/exercise-descriptions")
public class ExerciseDescriptionController {
	
	private final ExerciseDescriptionQueryApplicationService exerciseDescriptionQueryService;
	private final ExerciseDescriptionCreationApplicationService exerciseDescriptionCreationService;
	private final ExerciseDescriptionUpdateApplicationService exerciseDescriptionUpdateService;
	private final ExerciseDescriptionDeletionApplicationService exerciseDescriptionDeleteService;
	
	public ExerciseDescriptionController(
			ExerciseDescriptionQueryApplicationService exerciseDescriptionQueryService, 
			ExerciseDescriptionCreationApplicationService exerciseDescriptionCreationService,
			ExerciseDescriptionUpdateApplicationService exerciseDescriptionUpdateService,
			ExerciseDescriptionDeletionApplicationService exerciseDescriptionDeleteService) {
		
		this.exerciseDescriptionQueryService = exerciseDescriptionQueryService;
		this.exerciseDescriptionCreationService = exerciseDescriptionCreationService;
		this.exerciseDescriptionUpdateService = exerciseDescriptionUpdateService;
		this.exerciseDescriptionDeleteService = exerciseDescriptionDeleteService;
	}
	
	@GetMapping("")
	public List<ExerciseDescriptionDto> findAll() {
		return exerciseDescriptionQueryService.findAll();
	}
	
	@PostMapping("")
	public void create(@RequestBody ExerciseDescriptionDto description) throws DtoConversionException {
		exerciseDescriptionCreationService.create(description);
	}
	
	@PutMapping("")
	public void update(@RequestBody ExerciseDescriptionDto description) throws NonexistentExerciseDescriptionException {
		exerciseDescriptionUpdateService.update(description);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable("id") String id) throws UndeletableExerciseDescriptionException {
		exerciseDescriptionDeleteService.deleteById(id);
	}

}
