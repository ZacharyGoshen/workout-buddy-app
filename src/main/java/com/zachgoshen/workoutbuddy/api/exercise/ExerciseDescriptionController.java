package com.zachgoshen.workoutbuddy.api.exercise;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionCreationUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionDeletionUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionQueryUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionUpdate;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionUpdateUseCase;
import com.zachgoshen.workoutbuddy.application.exercise.NonexistentExerciseDescriptionException;
import com.zachgoshen.workoutbuddy.application.exercise.UndeletableExerciseDescriptionException;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionSortOrder;

@RestController
@RequestMapping("/api/exercise-descriptions")
public class ExerciseDescriptionController {
	
	private final ExerciseDescriptionQueryUseCase queryUseCase;
	private final ExerciseDescriptionCreationUseCase creationUseCase;
	private final ExerciseDescriptionUpdateUseCase updateUseCase;
	private final ExerciseDescriptionDeletionUseCase deletionUseCase;
	
	public ExerciseDescriptionController(
			ExerciseDescriptionQueryUseCase queryUseCase, 
			ExerciseDescriptionCreationUseCase creationUseCase,
			ExerciseDescriptionUpdateUseCase updateUseCase,
			ExerciseDescriptionDeletionUseCase deletionUseCase) {
		
		this.queryUseCase = queryUseCase;
		this.creationUseCase = creationUseCase;
		this.updateUseCase = updateUseCase;
		this.deletionUseCase = deletionUseCase;
	}
	
	@GetMapping("")
	public List<ExerciseDescriptionDto> findAll(
			@RequestParam(name = "name", required = false) String name,
			@RequestParam(name = "sortBy", required = false) String sortBy) 
			throws DtoConversionException {
		
		ExerciseDescriptionSortOrder sortOrder;
		if (sortBy == null) {
			sortOrder = ExerciseDescriptionSortOrder.NAME_ALPHABETICALLY;
		} else {
			sortOrder = ExerciseDescriptionSortOrderAssembler.assemble(sortBy);
		}
		
		return queryUseCase.findSortedByPartialName(name, sortOrder)
			.stream()
			.map(description -> ExerciseDescriptionConverter.toDto(description))
			.collect(Collectors.toList());
	}
	
	@PostMapping("")
	public void create(@RequestBody ExerciseDescriptionDto dto) throws DtoConversionException {
		ExerciseDescription description = ExerciseDescriptionConverter.toEntity(dto);
		creationUseCase.create(description);
	}
	
	@PatchMapping("/{id}")
	public void update(@PathVariable("id") String id, @RequestBody ExerciseDescriptionDto dto) throws DtoConversionException, NonexistentExerciseDescriptionException {
		ExerciseDescriptionUpdate modification = ExerciseDescriptionUpdateAssembler.assemble(dto);
		updateUseCase.updateById(id, modification);
	}
	
	@DeleteMapping("/{id}")
	public void deleteById(@PathVariable("id") String id) throws UndeletableExerciseDescriptionException {
		deletionUseCase.deleteById(id);
	}

}
