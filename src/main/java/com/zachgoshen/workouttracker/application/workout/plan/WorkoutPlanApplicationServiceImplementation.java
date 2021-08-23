package com.zachgoshen.workouttracker.application.workout.plan;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlan;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanRepository;

@Service
public class WorkoutPlanApplicationServiceImplementation implements WorkoutPlanApplicationService {
	
	private final WorkoutPlanRepository repository;
	
	private final WorkoutPlanConverter workoutPlanConverter = new WorkoutPlanConverter();
	
	public WorkoutPlanApplicationServiceImplementation(WorkoutPlanRepository repository) {
		this.repository = repository;
	}

	public List<WorkoutPlanDto> findAll() {
		List<WorkoutPlan> plans = repository.findAll();
		return buildDtosFromPlans(plans);
	}
	
	public WorkoutPlanDto findById(long id) {
		Optional<WorkoutPlan> plan = repository.findById(id);
		
		if (plan.isPresent()) {
			return workoutPlanConverter.convertFromModel(plan.get());
		} else {
			return null;
		}
	}
	
	private List<WorkoutPlanDto> buildDtosFromPlans(List<WorkoutPlan> plans) {
		return plans.stream()
			.map(plan -> workoutPlanConverter.convertFromModel(plan))
			.collect(Collectors.toList());
	}
	
}
