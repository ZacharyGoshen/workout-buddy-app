package com.zachgoshen.workoutbuddy.api.set;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.application.set.SetSortOrder;

public final class SetSortOrderAssembler {
	
	private SetSortOrderAssembler() {}
	
	public static SetSortOrder assemble(String sortOrder) throws DtoConversionException {
		if (sortOrder == null || sortOrder.equals("mostRecentCompletionTime")) {
			return SetSortOrder.MOST_RECENT_COMPLETION_TIME;
			
		} else if (sortOrder.equals("leastRecentCompletionTime")) {
			return SetSortOrder.LEAST_RECENT_COMPLETION_TIME;
			
		} else {
			String message = String.format("'%s' is not a valid sorting order for sets", sortOrder);
			throw new DtoConversionException(message);
		}
	}

}
