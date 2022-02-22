package com.zachgoshen.workoutbuddy.api.set;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.set.SetSortOrder;

public final class SetSortOrderAssembler {
	
	private SetSortOrderAssembler() {}
	
	public static SetSortOrder assemble(String sortOrder) throws DtoConversionException {
		switch (sortOrder) {
			case "leastRecentCompletionTime":
				return SetSortOrder.LEAST_RECENT_COMPLETION_TIME;
			case "mostRecentCompletionTime":
				return SetSortOrder.MOST_RECENT_COMPLETION_TIME;
			default:
				String message = String.format("'%s' is not a valid sorting order for sets", sortOrder);
				throw new DtoConversionException(message);
		}
	}

}
