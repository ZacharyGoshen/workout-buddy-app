package com.zachgoshen.workouttracker.application.workout;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/sets")
public class SetController {
	
	private final QuerySetsApplicationService querySetsService;
	
	public SetController(QuerySetsApplicationService querySetsService) {
		this.querySetsService = querySetsService;
	}
	
	@GetMapping("")
	public List<SetDto> findBy(@RequestParam(defaultValue = "") List<String> exerciseNames) {
		QuerySetsParameters parameters = new QuerySetsParameters(exerciseNames);
		
		return querySetsService.findBy(parameters);
	}

}
