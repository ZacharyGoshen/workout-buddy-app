package com.zachgoshen.workouttracker.data.workout.plan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkoutPlanJpaRepository extends JpaRepository<WorkoutPlanData, Long> {

}
