package com.zachgoshen.workouttracker.data.workout.plan;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlan;
import com.zachgoshen.workouttracker.domain.workout.plan.WorkoutPlanRepository;

@Repository
public class WorkoutPlanRepositoryImplementation implements WorkoutPlanRepository {
	
	private final WorkoutPlanJpaRepository jpaRepository;
	
	private final WorkoutPlanConverter workoutPlanConverter = new WorkoutPlanConverter();
	
	public WorkoutPlanRepositoryImplementation(WorkoutPlanJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public List<WorkoutPlan> findAll() {
		List<WorkoutPlanData> plansData = jpaRepository.findAll();
		
		return plansData.stream()
			.map(planData -> workoutPlanConverter.convertFromData(planData))
			.collect(Collectors.toList());
			
	}
	
	public Optional<WorkoutPlan> findById(long id) {
		Optional<WorkoutPlanData> planData = jpaRepository.findById(id);
		
		if (planData.isPresent()) {
			WorkoutPlan plan = workoutPlanConverter.convertFromData(planData.get());
			return Optional.of(plan);
		} else {
			return Optional.empty();
		}
	}

}
