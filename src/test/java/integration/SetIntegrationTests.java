package integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.zachgoshen.workoutbuddy.api.exercise.ExerciseSearchFilterDto;
import com.zachgoshen.workoutbuddy.api.set.SetSearchCriteriaDto;
import com.zachgoshen.workoutbuddy.api.set.SetSearchCriteriaDtos;
import com.zachgoshen.workoutbuddy.domain.common.date.Dates;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.set.Set;
import com.zachgoshen.workoutbuddy.domain.set.Sets;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.set.Superset;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class SetIntegrationTests extends IntegrationTests {
	
	@Test
	public void Search_SortByMostRecentCompletionTime() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		Date twoHoursAgo = Dates.nHoursAgo(2);
		benchPressSet.setTimeCompleted(twoHoursAgo);
		workout.appendSet(benchPressSet);
		
		SingleExerciseSet deadliftSet = Sets.deadliftSet();
		Date oneHourAgo = Dates.oneHourAgo();
		deadliftSet.setTimeCompleted(oneHourAgo);
		workout.appendSet(deadliftSet);
		
		SingleExerciseSet squatSet = Sets.squatSet();
		Date rightNow = Dates.rightNow();
		squatSet.setTimeCompleted(rightNow);
		workout.appendSet(squatSet);
		
		workoutRepository.save(workout);
		
		SetSearchCriteriaDto searchCriteria = new SetSearchCriteriaDto();
		searchCriteria.setSortBy("mostRecentCompletionTime");
		
		ResultActions resultActions = performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(3))));

		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, squatSet, 0);
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, deadliftSet, 1);
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 2);
	}
	
	@Test
	public void Search_SortByLeastRecentCompletionTime() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		Date twoHoursAgo = Dates.nHoursAgo(2);
		benchPressSet.setTimeCompleted(twoHoursAgo);
		workout.appendSet(benchPressSet);
		
		SingleExerciseSet deadliftSet = Sets.deadliftSet();
		Date oneHourAgo = Dates.oneHourAgo();
		deadliftSet.setTimeCompleted(oneHourAgo);
		workout.appendSet(deadliftSet);
		
		SingleExerciseSet squatSet = Sets.squatSet();
		Date rightNow = Dates.rightNow();
		squatSet.setTimeCompleted(rightNow);
		workout.appendSet(squatSet);
		
		workoutRepository.save(workout);
		
		SetSearchCriteriaDto searchCriteria = new SetSearchCriteriaDto();
		searchCriteria.setSortBy("leastRecentCompletionTime");
		
		ResultActions resultActions = performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(3))));

		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, deadliftSet, 1);
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, squatSet, 2);
	}
	
	@Test
	public void Search_AllCriteria_OneMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		SetSearchCriteriaDto searchCriteria = new SetSearchCriteriaDto();
		
		Optional<Float> timeRested = benchPressSet.getTimeRested();
		searchCriteria.setMinimumTimeRested(timeRested.get());
		searchCriteria.setMaximumTimeRested(timeRested.get());
		
		ExerciseSearchFilterDto exerciseFilter = new ExerciseSearchFilterDto();
		
		Exercise benchPress = benchPressSet.getExercise();
		
		ExerciseDescription benchPressDescription = benchPress.getDescription();
		String benchPressName = benchPressDescription.getName();
		exerciseFilter.setName(benchPressName);
		
		Optional<Float> weightUsed = benchPress.getWeightUsed();
		exerciseFilter.setMinimumWeightUsed(weightUsed.get());
		exerciseFilter.setMaximumWeightUsed(weightUsed.get());
		
		Optional<Integer> repsCompleted = benchPress.getRepsCompleted();
		exerciseFilter.setMinimumRepsCompleted(repsCompleted.get());
		exerciseFilter.setMaximumRepsCompleted(repsCompleted.get());
		
		Optional<Float> timePerformed = benchPress.getTimePerformed();
		exerciseFilter.setMinimumTimePerformed(timePerformed.get());
		exerciseFilter.setMaximumTimePerformed(timePerformed.get());
		
		List<ExerciseSearchFilterDto> exerciseFilters = Arrays.asList(exerciseFilter);
		searchCriteria.setExerciseFilters(exerciseFilters);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumTimeRested_OneMatchWithRestTimeEqualToMinimum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Optional<Float> timeRested = benchPressSet.getTimeRested();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumTimeRested(timeRested.get());
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumTimeRested_OneMatchWithRestTimeGreaterThanMinimum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Optional<Float> timeRested = benchPressSet.getTimeRested();
		Float timeRestedMinusFive = timeRested.get() - 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumTimeRested(timeRestedMinusFive);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumTimeRested_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Optional<Float> timeRested = benchPressSet.getTimeRested();
		Float timeRestedPlusFive = timeRested.get() + 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumTimeRested(timeRestedPlusFive);
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByMaximumTimeRested_OneMatchWithRestTimeEqualToMaximum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Optional<Float> timeRested = benchPressSet.getTimeRested();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumTimeRested(timeRested.get());
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMaximumTimeRested_OneMatchWithRestTimeLessThanMaximum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Optional<Float> timeRested = benchPressSet.getTimeRested();
		Float timeRestedPlusFive = timeRested.get() + 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumTimeRested(timeRestedPlusFive);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMaximumTimeRested_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Optional<Float> timeRested = benchPressSet.getTimeRested();
		Float timeRestedMinusFive = timeRested.get() - 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumTimeRested(timeRestedMinusFive);
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByExerciseName_OneMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		ExerciseDescription benchPressDescription = benchPress.getDescription();
		String benchPressName = benchPressDescription.getName();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byExerciseName(benchPressName);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByExerciseName_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byExerciseName("Non-matching Set Name");
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByMinimumWeightUsed_OneMatchWithWeightEqualToMinimum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> weightUsed = benchPress.getWeightUsed();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumWeightUsed(weightUsed.get());
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumWeightUsed_OneMatchWithWeightGreaterThanMinimum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> weightUsed = benchPress.getWeightUsed();
		Float weightUsedMinusFive = weightUsed.get() - 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumWeightUsed(weightUsedMinusFive);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumWeightUsed_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> weightUsed = benchPress.getWeightUsed();
		Float weightUsedPlusFive = weightUsed.get() + 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumWeightUsed(weightUsedPlusFive);
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByMaximumWeightUsed_OneMatchWithWeightEqualToMaximum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> weightUsed = benchPress.getWeightUsed();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumWeightUsed(weightUsed.get());
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMaximumWeightUsed_OneMatchWithWeightLessThanMaximum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> weightUsed = benchPress.getWeightUsed();
		Float weightUsedPlusFive = weightUsed.get() + 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumWeightUsed(weightUsedPlusFive);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMaximumWeightUsed_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> weightUsed = benchPress.getWeightUsed();
		Float weightUsedMinusFive = weightUsed.get() - 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumWeightUsed(weightUsedMinusFive);
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByMinimumRepsCompleted_OneMatchWithRepsEqualToMinimum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Integer> repsCompleted = benchPress.getRepsCompleted();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumRepsCompleted(repsCompleted.get());
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumRepsCompleted_OneMatchWithRepsGreaterThanMinimum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Integer> repsCompleted = benchPress.getRepsCompleted();
		Integer repsCompletedMinusOne = repsCompleted.get() - 1;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumRepsCompleted(repsCompletedMinusOne);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumRepsCompleted_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Integer> repsCompleted = benchPress.getRepsCompleted();
		Integer repsCompletedPlusOne = repsCompleted.get() + 1;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumRepsCompleted(repsCompletedPlusOne);
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByMaximumRepsCompleted_OneMatchWithRepsEqualToMaximum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Integer> repsCompleted = benchPress.getRepsCompleted();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumRepsCompleted(repsCompleted.get());
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMaximumRepsCompleted_OneMatchWithRepsLessThanMaximum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Integer> repsCompleted = benchPress.getRepsCompleted();
		Integer repsCompletedPlusOne = repsCompleted.get() + 1;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumRepsCompleted(repsCompletedPlusOne);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMaximumRepsCompleted_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Integer> repsCompleted = benchPress.getRepsCompleted();
		Integer repsCompletedMinusOne = repsCompleted.get() - 1;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumRepsCompleted(repsCompletedMinusOne);
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByMinimumTimePerformed_OneMatchWithDurationEqualToMinimum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> timePerformed = benchPress.getTimePerformed();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumTimePerformed(timePerformed.get());
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumTimePerformed_OneMatchWithDurationGreaterThanMinimum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> timePerformed = benchPress.getTimePerformed();
		Float timePerformedMinusFive = timePerformed.get() - 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumTimePerformed(timePerformedMinusFive);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMinimumTimePerformed_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> timePerformed = benchPress.getTimePerformed();
		Float timePerformedPlusFive = timePerformed.get() + 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMinimumTimePerformed(timePerformedPlusFive);
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByMaximumTimePerformed_OneMatchWithDurationEqualToMaximum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> timePerformed = benchPress.getTimePerformed();
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumTimePerformed(timePerformed.get());
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMaximumTimePerformed_OneMatchWithDurationLessThanMaximum() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> timePerformed = benchPress.getTimePerformed();
		Float timePerformedPlusFive = timePerformed.get() + 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumTimePerformed(timePerformedPlusFive);
		
		ResultActions resultActions = performSearch(searchCriteria).andExpect(status().is2xxSuccessful());
		
		assertResponseContainsSetWithWorkoutDetailsAtIndex(resultActions, workout, benchPressSet, 0);
	}
	
	@Test
	public void Search_ByMaximumTimePerformed_NoMatch() throws Exception {
		Workout workout = new Workout();
		
		SingleExerciseSet benchPressSet = Sets.benchPressSet();
		workout.appendSet(benchPressSet);
		
		workoutRepository.save(workout);
		
		Exercise benchPress = benchPressSet.getExercise();
		Optional<Float> timePerformed = benchPress.getTimePerformed();
		Float timePerformedMinusFive = timePerformed.get() - 5;
		SetSearchCriteriaDto searchCriteria = SetSearchCriteriaDtos.byMaximumTimePerformed(timePerformedMinusFive);
		
		performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Search_ByMultipleExerciseNames_MatchesSuperset() throws Exception {
		Workout workout = new Workout();
		
		Superset benchPressIntoDipSuperset = Sets.benchPressIntoDipSuperset();
		workout.appendSet(benchPressIntoDipSuperset);
		
		workoutRepository.save(workout);
		
		SetSearchCriteriaDto searchCriteria = new SetSearchCriteriaDto();
		
		List<Exercise> exercises = benchPressIntoDipSuperset.getExercises();
		
		ExerciseSearchFilterDto benchPressFilter = new ExerciseSearchFilterDto();
		Exercise benchPress = exercises.get(0);
		ExerciseDescription benchPressDescription = benchPress.getDescription();
		String benchPressName = benchPressDescription.getName();
		benchPressFilter.setName(benchPressName);
		
		ExerciseSearchFilterDto dipFilter = new ExerciseSearchFilterDto();
		Exercise dip = exercises.get(1);
		ExerciseDescription dipDescription = dip.getDescription();
		String dipName = dipDescription.getName();
		dipFilter.setName(dipName);
		
		List<ExerciseSearchFilterDto> exerciseFilters = Arrays.asList(benchPressFilter, dipFilter);
		searchCriteria.setExerciseFilters(exerciseFilters);
		
		ResultActions resultActions = performSearch(searchCriteria)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(1))));
		
		assertResponseContainsSetAtIndex(resultActions, benchPressIntoDipSuperset, 0);
	}
	
	@Test
	public void Search_InvalidSortingOrder() throws Exception {
		SetSearchCriteriaDto searchCriteria = new SetSearchCriteriaDto();
		searchCriteria.setSortBy("invalid sorting order");
		
		performSearch(searchCriteria)
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("DTO Conversion"))))
			.andExpect(jsonPath("$.message", is(equalTo("'invalid sorting order' is not a valid sorting order for sets"))));
	}
	
	private ResultActions performSearch(SetSearchCriteriaDto dto) throws Exception {
		String requestBody = buildSearchRequestBody(dto);
		
		MockHttpServletRequestBuilder requestBuilder = post("/api/sets/search")
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody);
		
		return mockMvc.perform(requestBuilder);
	}	
	
	private static String buildSearchRequestBody(SetSearchCriteriaDto dto) {
		String sortBy = dto.getSortBy();
		String sortByAsJsonValue = convertStringToJsonValue(sortBy);
		
		Float minimumTimeRested = dto.getMinimumTimeRested();
		String minimumTimeRestedAsJsonValue = convertFloatToJsonValue(minimumTimeRested);
		
		Float maximumTimeRested = dto.getMaximumTimeRested();
		String maximumTimeRestedAsJsonValue = convertFloatToJsonValue(maximumTimeRested);
		
		List<ExerciseSearchFilterDto> exerciseFilters = dto.getExerciseFilters();
		String exerciseFiltersAsJsonValue = convertExerciseSearchFilterDtosToJsonValue(exerciseFilters);
		
		return String.format(
			"{" +
				"\"sortBy\":%s," +
				"\"minimumTimeRested\":%s," +
				"\"maximumTimeRested\":%s," +
				"\"exerciseFilters\":%s" +
			"}", 
			sortByAsJsonValue,
			minimumTimeRestedAsJsonValue,
			maximumTimeRestedAsJsonValue,
			exerciseFiltersAsJsonValue);
	}
	
	private static String convertExerciseSearchFilterDtosToJsonValue(List<ExerciseSearchFilterDto> dtos) {
		if (dtos == null) {
			return "[]";
		} else {
			List<String> exerciseFiltersAsJsonValues = dtos.stream()
				.map(exerciseFilter -> convertExerciseSearchFilterDtoToJsonValue(exerciseFilter))
				.collect(Collectors.toList());
			
			return convertListOfJsonValuesToJsonList(exerciseFiltersAsJsonValues);
		}
	}
	
	private static String convertExerciseSearchFilterDtoToJsonValue(ExerciseSearchFilterDto dto) {
		String name = dto.getName();
		String nameAsJsonValue = convertStringToJsonValue(name);

		Float minimumWeightUsed = dto.getMinimumWeightUsed();
		String minimumWeightUsedAsJsonValue = convertFloatToJsonValue(minimumWeightUsed);

		Float maximumWeightUsed = dto.getMaximumWeightUsed();
		String maximumWeightUsedAsJsonValue = convertFloatToJsonValue(maximumWeightUsed);

		Integer minimumRepsCompleted = dto.getMinimumRepsCompleted();
		String minimumRepsCompletedAsJsonValue = convertIntegerToJsonValue(minimumRepsCompleted);

		Integer maximumRepsCompleted = dto.getMaximumRepsCompleted();
		String maximumRepsCompletedAsJsonValue = convertIntegerToJsonValue(maximumRepsCompleted);

		Float minimumTimePerformed = dto.getMinimumTimePerformed();
		String minimumTimePerformedAsJsonValue = convertFloatToJsonValue(minimumTimePerformed);

		Float maximumTimePerformed = dto.getMaximumTimePerformed();
		String maximumTimePerformedAsJsonValue = convertFloatToJsonValue(maximumTimePerformed);		
		
		return String.format(
			"{" +
				"\"name\":%s," +
				"\"minimumWeightUsed\":%s," +
				"\"maximumWeightUsed\":%s," +
				"\"minimumRepsCompleted\":%s," +
				"\"maximumRepsCompleted\":%s," +
				"\"minimumTimePerformed\":%s," +
				"\"maximumTimePerformed\":%s" +
			"}", 
			nameAsJsonValue,
			minimumWeightUsedAsJsonValue,
			maximumWeightUsedAsJsonValue,
			minimumRepsCompletedAsJsonValue,
			maximumRepsCompletedAsJsonValue,
			minimumTimePerformedAsJsonValue,
			maximumTimePerformedAsJsonValue);
	}
	
	private static void assertResponseContainsSetWithWorkoutDetailsAtIndex(
			ResultActions resultActions, 
			Workout workout,
			Set set, 
			int index
			) throws Exception {
		
		String pathToSet = String.format("$[%s]", index);
		
		String pathToWorkoutId = pathToSet + ".workoutId";
		String workoutId = workout.getId();
		assertResponseHasValueAtJsonPath(resultActions, pathToWorkoutId, workoutId);
		
		String pathToWorkoutName = pathToSet + ".workoutName";
		Optional<String> workoutName = workout.getName();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToWorkoutName, workoutName);
		
		String pathToWorkoutCompletionTime = pathToSet + ".workoutCompletionTime";
		Optional<Date> workoutCompletionTime = workout.getTimeCompleted();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToWorkoutCompletionTime, workoutCompletionTime);
		
		String pathToSetNumber = pathToSet + ".setNumber";
		int setNumber = getIndexOfSetInWorkout(set, workout);
		assertResponseHasValueAtJsonPath(resultActions, pathToSetNumber, setNumber);
		
		assertResponseContainsSetAtIndex(resultActions, set, index);
	}	
	
	private static int getIndexOfSetInWorkout(Set set, Workout workout) {
		String setId = set.getId();
		List<Set> workoutSets = workout.getSets();
		
		for (int i = 0; i < workoutSets.size(); i++) {
			Set workoutSet = workoutSets.get(i);
			String workoutSetId = workoutSet.getId();
			
			if (setId.equals(workoutSetId)) {
				return i;
			}
		}
		
		return -1;
	}
	
	private static void assertResponseContainsSetAtIndex(
			ResultActions resultActions, 
			Set set, 
			int index
			) throws Exception {
		
		String pathToSet = String.format("$[%s]", index);
		
		String pathToTimeCompleted = pathToSet + ".timeCompleted";
		Optional<Date> timeCompleted = set.getTimeCompleted();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToTimeCompleted, timeCompleted);
		
		String pathToTimeRested = pathToSet + ".timeRested";
		Optional<Float> timeRested = set.getTimeRested();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToTimeRested, timeRested);
		
		String pathToMinimumRestTimeAllowed = pathToSet + ".minimumRestTimeAllowed";
		Optional<Float> minimumRestTimeAllowed = set.getMinimumRestTimeAllowed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToMinimumRestTimeAllowed, minimumRestTimeAllowed);
		
		String pathToMaximumRestTimeAllowed = pathToSet + ".maximumRestTimeAllowed";
		Optional<Float> maximumRestTimeAllowed = set.getMaximumRestTimeAllowed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToMaximumRestTimeAllowed, maximumRestTimeAllowed);
		
		if (set instanceof SingleExerciseSet) {
			SingleExerciseSet singleExerciseSet = (SingleExerciseSet) set;
			assertResponseContainsSingleExerciseSetAtIndex(resultActions, singleExerciseSet, index);
		} else if (set instanceof Superset) {
			Superset superset = (Superset) set;
			assertResponseContainsSupersetAtIndex(resultActions, superset, index);
		}
	}
	
	private static void assertResponseContainsSingleExerciseSetAtIndex(
			ResultActions resultActions, 
			SingleExerciseSet set, 
			int index
			) throws Exception {
		
		String pathToSet = String.format("$[%s]", index);
		
		String pathToType = pathToSet + ".type";
		assertResponseHasValueAtJsonPath(resultActions, pathToType, "Single Exercise Set");
		
		Exercise exercise = set.getExercise();
		assertResponseContainsExerciseAtIndex(resultActions, exercise, index, 0);
	}
	
	private static void assertResponseContainsSupersetAtIndex(
			ResultActions resultActions, 
			Superset set, 
			int index
			) throws Exception {
		
		String pathToSet = String.format("$[%s]", index);
		
		String pathToType = pathToSet + ".type";
		assertResponseHasValueAtJsonPath(resultActions, pathToType, "Superset");
		
		List<Exercise> exercises = set.getExercises();
		
		for (int i = 0; i < exercises.size(); i++) {
			Exercise exercise = exercises.get(i);
			assertResponseContainsExerciseAtIndex(resultActions, exercise, index, i);
		}
	}
	
	private static void assertResponseContainsExerciseAtIndex(
			ResultActions resultActions, 
			Exercise exercise, 
			int setIndex,
			int exerciseIndex
			) throws Exception {
		
		String pathToExercise = String.format("$[%s].exercises[%s]", setIndex, exerciseIndex);
		
		ExerciseDescription exerciseDescription = exercise.getDescription();
		
		String pathToName = pathToExercise + ".name";
		String name = exerciseDescription.getName();
		assertResponseHasValueAtJsonPath(resultActions, pathToName, name);
		
		String pathToWeightUsed = pathToExercise + ".weightUsed";
		Optional<Float> weightUsed = exercise.getWeightUsed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToWeightUsed, weightUsed);
		
		String pathToMinimumWeightAllowed = pathToExercise + ".minimumWeightAllowed";
		Optional<Float> minimumWeightAllowed = exercise.getMinimumWeightAllowed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToMinimumWeightAllowed, minimumWeightAllowed);
		
		String pathToMaximumWeightAllowed = pathToExercise + ".maximumWeightAllowed";
		Optional<Float> maximumWeightAllowed = exercise.getMaximumWeightAllowed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToMaximumWeightAllowed, maximumWeightAllowed);
		
		String pathToRepsCompleted = pathToExercise + ".repsCompleted";
		Optional<Integer> repsCompleted = exercise.getRepsCompleted();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToRepsCompleted, repsCompleted);
		
		String pathToMinimumRepsAllowed = pathToExercise + ".minimumRepsAllowed";
		Optional<Integer> minimumRepsAllowed = exercise.getMinimumRepsAllowed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToMinimumRepsAllowed, minimumRepsAllowed);
		
		String pathToMaximumRepsAllowed = pathToExercise + ".maximumRepsAllowed";
		Optional<Integer> maximumRepsAllowed = exercise.getMaximumRepsAllowed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToMaximumRepsAllowed, maximumRepsAllowed);
		
		String pathToTimePerformed = pathToExercise + ".timePerformed";
		Optional<Float> timePerformed = exercise.getTimePerformed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToTimePerformed, timePerformed);
		
		String pathToMinimumDurationAllowed = pathToExercise + ".minimumDurationAllowed";
		Optional<Float> minimumDurationAllowed = exercise.getMinimumDurationAllowed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToMinimumDurationAllowed, minimumDurationAllowed);
		
		String pathToMaximumDurationAllowed = pathToExercise + ".maximumDurationAllowed";
		Optional<Float> maximumDurationAllowed = exercise.getMaximumDurationAllowed();
		assertResponseHasValueAtJsonPathIfOptionalIsPresent(resultActions, pathToMaximumDurationAllowed, maximumDurationAllowed);
	}

}
