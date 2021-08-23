package com.zachgoshen.workouttracker.application.workout.session;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSession;
import com.zachgoshen.workouttracker.domain.workout.session.WorkoutSessionRepository;

@Service
public class WorkoutSessionApplicationServiceImplementation implements WorkoutSessionApplicationService {

	private final WorkoutSessionRepository repository;
	
	private final WorkoutSessionConverter workoutSessionConverter = new WorkoutSessionConverter();
	
	public WorkoutSessionApplicationServiceImplementation(WorkoutSessionRepository repository) {
		this.repository = repository;
	}

	public List<WorkoutSessionDto> findAll() {
		List<WorkoutSession> sessions = repository.findAll();
		return buildDtosFromSessions(sessions);
	}
	
	private List<WorkoutSessionDto> buildDtosFromSessions(List<WorkoutSession> sessions) {
		return sessions.stream()
			.map(session -> workoutSessionConverter.convertFromModel(session))
			.collect(Collectors.toList());
	}

	public WorkoutSessionDto findById(long id) {
		Optional<WorkoutSession> session = repository.findById(id);
		
		if (session.isPresent()) {
			return workoutSessionConverter.convertFromModel(session.get());
		} else {
			return null;
		}
	}

}
