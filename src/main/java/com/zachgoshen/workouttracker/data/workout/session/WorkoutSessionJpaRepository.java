package com.zachgoshen.workouttracker.data.workout.session;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkoutSessionJpaRepository extends JpaRepository<WorkoutSessionData, Long> {

}
