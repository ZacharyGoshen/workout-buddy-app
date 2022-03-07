package com.zachgoshen.workoutbuddy;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.zachgoshen.workoutbuddy.data.DataPopulator;
import com.zachgoshen.workoutbuddy.domain.common.math.InvalidRangeException;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

@Configuration
@Profile("!test")
public class DataPopulatorConfiguration implements ApplicationListener<ApplicationReadyEvent> {
	
	private final WorkoutRepository workoutRepository;
	private final ExerciseDescriptionRepository exerciseDescriptionRepository;
	
	public DataPopulatorConfiguration(WorkoutRepository workoutRepository, ExerciseDescriptionRepository exerciseDescriptionRepository) {
		this.workoutRepository = workoutRepository;
		this.exerciseDescriptionRepository = exerciseDescriptionRepository;
	}

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		DataPopulator populator = new DataPopulator(workoutRepository, exerciseDescriptionRepository);
		
		try {
			populator.populate();
		} catch (InvalidRangeException e) {
			e.printStackTrace();
		}
	}

}
