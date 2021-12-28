package com.zachgoshen.workouttracker.application.set;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
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
	
	@GetMapping("")
	public List<SetWithWorkoutDetailsDto> findAll() {
		return setQueryService.findAll();
	}
	
	@PostMapping("/search")
	public List<SetWithWorkoutDetailsDto> findByCriteria(@RequestBody SetSearchCriteriaDto criteria) {
		return setQueryService.findBy(criteria);
	}

}
