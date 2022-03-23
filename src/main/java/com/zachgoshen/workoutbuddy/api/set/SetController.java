package com.zachgoshen.workoutbuddy.api.set;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.zachgoshen.workoutbuddy.api.DtoConversionException;
import com.zachgoshen.workoutbuddy.application.set.SetQueryUseCase;
import com.zachgoshen.workoutbuddy.application.set.SetSortOrder;
import com.zachgoshen.workoutbuddy.domain.common.specification.Specification;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

@RestController
@RequestMapping("/api/sets")
public class SetController {
	
	private final SetQueryUseCase queryUseCase;
	
	public SetController(SetQueryUseCase queryUseCase) {
		this.queryUseCase = queryUseCase;
	}
	
	@PostMapping("/search")
	public List<SetWithWorkoutDetailsDto> findByCriteria(@RequestBody SetSearchCriteriaDto criteria) throws DtoConversionException {
		Specification<Set> specification = SetSpecificationAssembler.assemble(criteria);
		
		String sortBy = criteria.getSortBy();
		SetSortOrder sortOrder = SetSortOrderAssembler.assemble(sortBy);
		
		Map<Set, Workout> setToWorkout = queryUseCase.findSortedBySpecification(specification, sortOrder);
		
		List<SetWithWorkoutDetailsDto> setsWithWorkoutDetails = new ArrayList<>();
		
		for (Entry<Set, Workout> entry: setToWorkout.entrySet()) {
			SetWithWorkoutDetailsDto setWithWorkoutDetails = SetWithWorkoutDetailsDtoAssembler.assemble(entry.getKey(), entry.getValue());
			setsWithWorkoutDetails.add(setWithWorkoutDetails);
		}
		
		return setsWithWorkoutDetails;
	}

}
