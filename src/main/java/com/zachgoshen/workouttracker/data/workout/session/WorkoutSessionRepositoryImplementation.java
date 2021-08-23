package com.zachgoshen.workouttracker.data.workout.session;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSession;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSessionRepository;

@Repository
public class WorkoutSessionRepositoryImplementation implements WorkoutSessionRepository {
	
	private final WorkoutSessionJpaRepository jpaRepository;
	
	private final WorkoutSessionConverter workoutSessionConverter = new WorkoutSessionConverter();
	
	public WorkoutSessionRepositoryImplementation(WorkoutSessionJpaRepository jpaRepository) {
		this.jpaRepository = jpaRepository;
	}

	public List<WorkoutSession> findAll() {
		List<WorkoutSessionData> sessionsData = jpaRepository.findAll();
		
		return sessionsData.stream()
			.map(sessionData -> workoutSessionConverter.convertFromData(sessionData))
			.collect(Collectors.toList());
	}

	public Optional<WorkoutSession> findById(long id) {
		Optional<WorkoutSessionData> sessionData = jpaRepository.findById(id);
		
		if (sessionData.isPresent()) {
			WorkoutSession session = workoutSessionConverter.convertFromData(sessionData.get());
			return Optional.of(session);
		} else {
			return Optional.empty();
		}
	}

}
