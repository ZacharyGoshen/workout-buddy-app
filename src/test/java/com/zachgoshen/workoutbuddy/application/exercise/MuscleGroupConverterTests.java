package com.zachgoshen.workoutbuddy.application.exercise;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.api.exercise.MuscleGroupConverter;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public class MuscleGroupConverterTests {
	
	@Test
	public void ToString_Abs() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.ABS);
		assertEquals("Abs", muscleGroup);
	}
	
	@Test
	public void ToString_Biceps() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.BICEPS);
		assertEquals("Biceps", muscleGroup);
	}
	
	@Test
	public void ToString_Calves() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.CALVES);
		assertEquals("Calves", muscleGroup);
	}
	
	@Test
	public void ToString_Delts() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.DELTS);
		assertEquals("Delts", muscleGroup);
	}
	
	@Test
	public void ToString_Glutes() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.GLUTES);
		assertEquals("Glutes", muscleGroup);
	}
	
	@Test
	public void ToString_Hamstrings() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.HAMSTRINGS);
		assertEquals("Hamstrings", muscleGroup);
	}
	
	@Test
	public void ToString_Lats() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.LATS);
		assertEquals("Lats", muscleGroup);
	}
	
	@Test
	public void ToString_LowerBack() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.LOWER_BACK);
		assertEquals("Lower Back", muscleGroup);
	}
	
	@Test
	public void ToString_Obliques() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.OBLIQUES);
		assertEquals("Obliques", muscleGroup);
	}
	
	@Test
	public void ToString_Pecs() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.PECS);
		assertEquals("Pecs", muscleGroup);
	}
	
	@Test
	public void ToString_Quads() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.QUADS);
		assertEquals("Quads", muscleGroup);
	}
	
	@Test
	public void ToString_Traps() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.TRAPS);
		assertEquals("Traps", muscleGroup);
	}
	
	@Test
	public void ToString_Triceps() {
		String muscleGroup = MuscleGroupConverter.toString(MuscleGroup.TRICEPS);
		assertEquals("Triceps", muscleGroup);
	}
	
	@Test
	public void ToEnum_Abs() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Abs");
		assertEquals(MuscleGroup.ABS, muscleGroup);
	}
	
	@Test
	public void ToEnum_Biceps() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Biceps");
		assertEquals(MuscleGroup.BICEPS, muscleGroup);
	}
	
	@Test
	public void ToEnum_Calves() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Calves");
		assertEquals(MuscleGroup.CALVES, muscleGroup);
	}
	
	@Test
	public void ToEnum_Delts() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Delts");
		assertEquals(MuscleGroup.DELTS, muscleGroup);
	}
	
	@Test
	public void ToEnum_Glutes() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Glutes");
		assertEquals(MuscleGroup.GLUTES, muscleGroup);
	}
	
	@Test
	public void ToEnum_Hamstrings() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Hamstrings");
		assertEquals(MuscleGroup.HAMSTRINGS, muscleGroup);
	}
	
	@Test
	public void ToEnum_Lats() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Lats");
		assertEquals(MuscleGroup.LATS, muscleGroup);
	}
	
	@Test
	public void ToEnum_LowerBack() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Lower Back");
		assertEquals(MuscleGroup.LOWER_BACK, muscleGroup);
	}
	
	@Test
	public void ToEnum_Obliques() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Obliques");
		assertEquals(MuscleGroup.OBLIQUES, muscleGroup);
	}
	
	@Test
	public void ToEnum_Pecs() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Pecs");
		assertEquals(MuscleGroup.PECS, muscleGroup);
	}
	
	@Test
	public void ToEnum_Quads() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Quads");
		assertEquals(MuscleGroup.QUADS, muscleGroup);
	}
	
	@Test
	public void ToEnum_Traps() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Traps");
		assertEquals(MuscleGroup.TRAPS, muscleGroup);
	}
	
	@Test
	public void ToEnum_Triceps() throws DtoConversionException {
		MuscleGroup muscleGroup = MuscleGroupConverter.toEnum("Triceps");
		assertEquals(MuscleGroup.TRICEPS, muscleGroup);
	}
	
	@Test
	public void ToEnum_StringDoesntCorrespondToAnyMuscleGroup_ThrowsDtoConversionException() throws DtoConversionException {
		assertThrows(DtoConversionException.class, () -> MuscleGroupConverter.toEnum("Invalid"));
	}

}
