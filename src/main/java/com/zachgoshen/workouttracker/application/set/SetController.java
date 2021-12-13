package com.zachgoshen.workouttracker.application.set;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sets")
public class SetController {
	
	private final SetQueryApplicationService setQueryService;
	
	public SetController(SetQueryApplicationService setQueryService) {
		this.setQueryService = setQueryService;
	}
	
	@GetMapping("")
	public List<SetWithWorkoutDetailsDto> findBy(@RequestParam(defaultValue = "") List<String> exerciseNames) {
		SetQueryParameters parameters = new SetQueryParameters(exerciseNames);
		
		return setQueryService.findBy(parameters);
	}

}
