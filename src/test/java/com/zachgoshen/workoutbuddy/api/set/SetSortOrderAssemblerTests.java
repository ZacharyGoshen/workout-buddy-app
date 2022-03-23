package com.zachgoshen.workoutbuddy.api.set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.application.set.SetSortOrder;

public class SetSortOrderAssemblerTests {
	
	@Test
	public void Assemble_LeastRecentCompletionTime() throws DtoConversionException {
		SetSortOrder sortOrder = SetSortOrderAssembler.assemble("leastRecentCompletionTime");
		
		assertEquals(SetSortOrder.LEAST_RECENT_COMPLETION_TIME, sortOrder);
	}
	
	@Test
	public void Assemble_MostRecentCompletionTime() throws DtoConversionException {
		SetSortOrder sortOrder = SetSortOrderAssembler.assemble("mostRecentCompletionTime");
		
		assertEquals(SetSortOrder.MOST_RECENT_COMPLETION_TIME, sortOrder);
	}
	
	@Test
	public void Assemble_InvalidSortOrder_ThrowsDtoConversionException() throws DtoConversionException {
		DtoConversionException exception = assertThrows(
			DtoConversionException.class, 
			() -> SetSortOrderAssembler.assemble("invalidSortOrder"));
		
		assertEquals("'invalidSortOrder' is not a valid sorting order for sets", exception.getMessage());
	}

}
