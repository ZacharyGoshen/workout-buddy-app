package com.zachgoshen.workoutbuddy.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.zachgoshen.workoutbuddy.application.exercise.UndeletableExerciseDescriptionException;

@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity<ErrorDto> handleDtoConversionException(DtoConversionException exception) {
		String type = "DTO Conversion";
		String message = exception.getMessage();
		
		ErrorDto dto = new ErrorDto(type, message);
		
		return new ResponseEntity<ErrorDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler
	public ResponseEntity<ErrorDto> handleUndeletableExerciseDescriptionException(UndeletableExerciseDescriptionException exception) {
		String type = "Undeletable Exercise Description";
		String message = "The exercise description can't be deleted because it belongs to one or more workouts.";
		
		ErrorDto dto = new ErrorDto(type, message);
		
		return new ResponseEntity<ErrorDto>(dto, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}
