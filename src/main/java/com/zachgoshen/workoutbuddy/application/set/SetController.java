package com.zachgoshen.workoutbuddy.application.set;

import java.util.List;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sets")
public class SetController {
	
	private final SetQueryApplicationService setQueryService;
	
	public SetController(SetQueryApplicationService setQueryService) {
		this.setQueryService = setQueryService;
	}
	
	@PostMapping("/search")
	public List<SetWithWorkoutDetailsDto> findByCriteria(@RequestBody SetSearchCriteriaDto criteria) {
		return setQueryService.findBy(criteria);
	}

}
