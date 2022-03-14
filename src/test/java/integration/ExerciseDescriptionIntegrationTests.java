package integration;

import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.zachgoshen.workoutbuddy.WorkoutBuddyApplication;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workoutbuddy.api.exercise.MuscleGroupConverter;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;
import com.zachgoshen.workoutbuddy.domain.workout.WorkoutRepository;

@SpringBootTest(classes = WorkoutBuddyApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ExerciseDescriptionIntegrationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private WorkoutRepository workoutRepository;
	
	@Autowired
	private ExerciseDescriptionRepository exerciseDescriptionRepository;
	
	@AfterEach
	public void deleteAllData() {
		workoutRepository.deleteAll();
		exerciseDescriptionRepository.deleteAll();
	}
	
	@Test
	public void Get_NoParameters_NoDescriptionsExist() throws Exception {
		performGet(null, null)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Get_NoParameters_SingleDescriptionExists() throws Exception {
		ExerciseDescription description = buildBenchPress();
		exerciseDescriptionRepository.save(description);
		
		ResultActions resultActions = performGet(null, null)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(1))));
		
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, description, 0);
	}
	
	@Test
	public void Get_NoParameters_MultipleDescriptionsExist() throws Exception {
		ExerciseDescription squat = buildSquat();
		exerciseDescriptionRepository.save(squat);
		
		ExerciseDescription benchPress = buildBenchPress();
		exerciseDescriptionRepository.save(benchPress);
		
		ExerciseDescription deadlift = buildDeadlift();
		exerciseDescriptionRepository.save(deadlift);
		
		ResultActions resultActions = performGet(null, null)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(3))));
		
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, benchPress, 0);
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, deadlift, 1);
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, squat, 2);
	}
	
	@Test
	public void Get_SortByNameAlphabetically() throws Exception {
		ExerciseDescription squat = buildSquat();
		exerciseDescriptionRepository.save(squat);
		
		ExerciseDescription benchPress = buildBenchPress();
		exerciseDescriptionRepository.save(benchPress);
		
		ExerciseDescription deadlift = buildDeadlift();
		exerciseDescriptionRepository.save(deadlift);
		
		ResultActions resultActions = performGet(null, "nameAlphabetically")
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(3))));
		
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, benchPress, 0);
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, deadlift, 1);
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, squat, 2);
	}
	
	@Test
	public void Get_SortByNameReverseAlphabetically() throws Exception {
		ExerciseDescription squat = buildSquat();
		exerciseDescriptionRepository.save(squat);
		
		ExerciseDescription benchPress = buildBenchPress();
		exerciseDescriptionRepository.save(benchPress);
		
		ExerciseDescription deadlift = buildDeadlift();
		exerciseDescriptionRepository.save(deadlift);
		
		ResultActions resultActions = performGet(null, "nameReverseAlphabetically")
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(3))));
		
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, squat, 0);
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, deadlift, 1);
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, benchPress, 2);
	}
	
	@Test
	public void Get_InvalidSortByParameter() throws Exception {
		performGet(null, "invalidSortBy")
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("DTO Conversion"))))
			.andExpect(jsonPath("$.message", is(equalTo("'invalidSortBy' is not a valid sorting order for exercise descriptions"))));
	}
	
	@Test
	public void Get_SearchByName() throws Exception {
		ExerciseDescription squat = buildSquat();
		exerciseDescriptionRepository.save(squat);
		
		ExerciseDescription benchPress = buildBenchPress();
		exerciseDescriptionRepository.save(benchPress);
		
		ExerciseDescription deadlift = buildDeadlift();
		exerciseDescriptionRepository.save(deadlift);
		
		ResultActions resultActions = performGet("bench", null)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(1))));
		
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, benchPress, 0);
	}
	
	@Test
	public void Post_ValidRequest() throws Exception {
		ExerciseDescriptionDto dto = buildBenchPressDto();
		
		performPost(dto).andExpect(status().is2xxSuccessful());
		
		List<ExerciseDescription> allDescriptions = exerciseDescriptionRepository.findAll();
		ExerciseDescription newDescription = allDescriptions.get(0);
		
		assertNotNull(newDescription.getId());
		assertEquals(newDescription.getName(), dto.getName());
		assertEquals(newDescription.getNotes(), dto.getNotes());
		assertEquals(newDescription.getMuscleGroups().size(), 3);
		assertTrue(newDescription.getMuscleGroups().contains(MuscleGroup.DELTS));
		assertTrue(newDescription.getMuscleGroups().contains(MuscleGroup.PECS));
		assertTrue(newDescription.getMuscleGroups().contains(MuscleGroup.TRICEPS));
	}
	
	@Test
	public void Post_NullName() throws Exception {
		ExerciseDescriptionDto dto = buildBenchPressDto();
		dto.setName(null);
		
		performPost(dto)
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("DTO Conversion"))))
			.andExpect(jsonPath("$.message", is(equalTo("Name can't be null"))));
	}
	
	@Test
	public void Patch_DescriptionExists() throws Exception {
		ExerciseDescription existingDescription = buildBenchPress();
		exerciseDescriptionRepository.save(existingDescription);
		
		ExerciseDescriptionDto updateDto = buildDeadliftDto();
		
		String id = existingDescription.getId();
		performPatch(id, updateDto).andExpect(status().is2xxSuccessful());
		
		List<ExerciseDescription> allDescriptions = exerciseDescriptionRepository.findAll();
		ExerciseDescription updatedDescription = allDescriptions.get(0);
		
		assertNotNull(updatedDescription.getId());
		assertEquals(updatedDescription.getName(), updateDto.getName());
		assertEquals(updatedDescription.getNotes(), updateDto.getNotes());
		assertEquals(updatedDescription.getMuscleGroups().size(), 3);
		assertTrue(updatedDescription.getMuscleGroups().contains(MuscleGroup.GLUTES));
		assertTrue(updatedDescription.getMuscleGroups().contains(MuscleGroup.HAMSTRINGS));
		assertTrue(updatedDescription.getMuscleGroups().contains(MuscleGroup.LOWER_BACK));
	}
	
	@Test
	public void Patch_NullName() throws Exception {
		ExerciseDescription existingDescription = buildBenchPress();
		exerciseDescriptionRepository.save(existingDescription);
		
		ExerciseDescriptionDto dto = buildDeadliftDto();
		dto.setName(null);

		String id = existingDescription.getId();
		performPatch(id, dto)
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("DTO Conversion"))))
			.andExpect(jsonPath("$.message", is(equalTo("Name can't be null"))));
	}
	
	@Test
	public void Patch_DescriptionDoesntExist() throws Exception {
		ExerciseDescriptionDto dto = buildBenchPressDto();

		performPatch("nonexistent-id", dto)
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("Nonexistent Exercise Description"))))
			.andExpect(jsonPath("$.message", is(equalTo("No exercise description exists with id 'nonexistent-id'"))));
	}

	@Test
	public void Delete_DescriptionExists() throws Exception {
		ExerciseDescription existingDescription = buildBenchPress();
		exerciseDescriptionRepository.save(existingDescription);
		
		String id = existingDescription.getId();
		performDelete(id).andExpect(status().is2xxSuccessful());
		
		Optional<ExerciseDescription> deletedDescription = exerciseDescriptionRepository.findById(id);
		assertFalse(deletedDescription.isPresent());
	}

	@Test
	public void Delete_DescriptionDoesntExist() throws Exception {
		performDelete("nonexistent-id").andExpect(status().is2xxSuccessful());
	}

	@Test
	public void Delete_DescriptionBelongsToWorkout() throws Exception {
		ExerciseDescription description = buildBenchPress();
		exerciseDescriptionRepository.save(description);
		
		Exercise exercise = new Exercise(description);
		SingleExerciseSet set = new SingleExerciseSet(exercise);
		
		Workout workout = new Workout();
		workout.appendSet(set);
		workoutRepository.save(workout);
		
		String id = description.getId();
		performDelete(id)
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("Undeletable Exercise Description"))))
			.andExpect(jsonPath("$.message", is(equalTo("The exercise description can't be deleted because it belongs to one or more workouts"))));
	}
	
	private static ExerciseDescription buildBenchPress() {
		ExerciseDescription benchPress = new ExerciseDescription("Bench Press");
		benchPress.setNotes("Bench Press Notes");
		benchPress.addMuscleGroup(MuscleGroup.DELTS);
		benchPress.addMuscleGroup(MuscleGroup.PECS);
		benchPress.addMuscleGroup(MuscleGroup.TRICEPS);
		
		return benchPress;
	}
	
	private static ExerciseDescription buildDeadlift() {
		ExerciseDescription deadlift = new ExerciseDescription("Deadlift");
		deadlift.setNotes("Deadlift Notes");
		deadlift.addMuscleGroup(MuscleGroup.GLUTES);
		deadlift.addMuscleGroup(MuscleGroup.HAMSTRINGS);
		deadlift.addMuscleGroup(MuscleGroup.LOWER_BACK);
		
		return deadlift;
	}
	
	private static ExerciseDescription buildSquat() {
		ExerciseDescription squat = new ExerciseDescription("Squat");
		squat.setNotes("Squat Notes");
		squat.addMuscleGroup(MuscleGroup.GLUTES);
		squat.addMuscleGroup(MuscleGroup.HAMSTRINGS);
		squat.addMuscleGroup(MuscleGroup.LOWER_BACK);
		squat.addMuscleGroup(MuscleGroup.QUADS);
		
		return squat;
	}
	
	private static ExerciseDescriptionDto buildBenchPressDto() {
		ExerciseDescriptionDto benchPress = new ExerciseDescriptionDto();
		benchPress.setName("Bench Press");
		benchPress.setNotes("Bench Press Notes");
		
		List<String> muscleGroups = Arrays.asList("Delts", "Pecs", "Triceps");
		benchPress.setMuscleGroups(muscleGroups);
		
		return benchPress;
	}
	
	private static ExerciseDescriptionDto buildDeadliftDto() {
		ExerciseDescriptionDto deadlift = new ExerciseDescriptionDto();
		deadlift.setName("Deadlift");
		deadlift.setNotes("Deadlift Notes");
		
		List<String> muscleGroups = Arrays.asList("Glutes", "Hamstrings", "Lower Back");
		deadlift.setMuscleGroups(muscleGroups);
		
		return deadlift;
	}
	
	private ResultActions performGet(String name, String sortBy) throws Exception {
		MockHttpServletRequestBuilder requestBuilder = get("/api/exercise-descriptions")
			.param("name", name)
			.param("sortBy", sortBy);
		
		return mockMvc.perform(requestBuilder);
	}
	
	private ResultActions performPost(ExerciseDescriptionDto dto) throws Exception {
		String requestBody = buildPostOrPatchRequestBody(dto);
		
		MockHttpServletRequestBuilder requestBuilder = post("/api/exercise-descriptions")
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody);
		
		return mockMvc.perform(requestBuilder);
	}
	
	private ResultActions performPatch(String id, ExerciseDescriptionDto dto) throws Exception {
		String url = String.format("/api/exercise-descriptions/%s", id);
		String requestBody = buildPostOrPatchRequestBody(dto);
		
		MockHttpServletRequestBuilder requestBuilder = patch(url)
			.contentType(MediaType.APPLICATION_JSON)
			.content(requestBody);
		
		return mockMvc.perform(requestBuilder);
	}
	
	private ResultActions performDelete(String id) throws Exception {
		String url = String.format("/api/exercise-descriptions/%s", id);
		
		MockHttpServletRequestBuilder requestBuilder = delete(url);
		
		return mockMvc.perform(requestBuilder);
	}
	
	private static String buildPostOrPatchRequestBody(ExerciseDescriptionDto dto) {
		String name = dto.getName();
		String nameAsJsonValue = convertStringToJsonValue(name);
		
		String notes = dto.getNotes();
		String notesAsJsonValue = convertStringToJsonValue(notes);
		
		List<String> muscleGroups = dto.getMuscleGroups();
		String muscleGroupsAsJsonValue = convertListToJsonValue(muscleGroups);
		
		return String.format(
			"{" +
				"\"name\":%s," +
				"\"notes\":%s," +
				"\"muscleGroups\":%s" +
			"}", 
			nameAsJsonValue,
			notesAsJsonValue,
			muscleGroupsAsJsonValue);
	}
	
	private static String convertStringToJsonValue(String string) {
		if (string == null) {
			return "null";
		} else {
			return String.format("\"%s\"", string);
		}
	}
	
	private static String convertListToJsonValue(List<String> list) {
		if (list == null) {
			return "[]";
		} else {
			String joinedItems = list.stream()
				.map(muscleGroup -> String.format("\"%s\"", muscleGroup))
				.collect(Collectors.joining(","));
			
			return String.format("[%s]", joinedItems);
		}
	}
	
	private static void assertResponseContainsExerciseDescriptionAtIndex(
			ResultActions resultActions, 
			ExerciseDescription description, 
			int index
			) throws Exception {
		
		String pathToExerciseDescription = String.format("$[%s]", index);
		
		String pathToId = pathToExerciseDescription + ".id";
		resultActions.andExpect(jsonPath(pathToId, is(not(equalTo(null)))));
		
		String pathToName = pathToExerciseDescription + ".name";
		resultActions.andExpect(jsonPath(pathToName, is(equalTo(description.getName()))));

		String pathToNotes = pathToExerciseDescription + ".notes";
		resultActions.andExpect(jsonPath(pathToNotes, is(equalTo(description.getNotes()))));
		
		String pathToMuscleGroups = pathToExerciseDescription + ".muscleGroups";
		
		String[] muscleGroups = description.getMuscleGroups()
			.stream()
			.map(muscleGroup -> MuscleGroupConverter.toString(muscleGroup))
			.collect(Collectors.toList())
			.toArray(new String[0]);

		resultActions.andExpect(jsonPath(pathToMuscleGroups + ".size()", is(equalTo(muscleGroups.length))));
		resultActions.andExpect(jsonPath(pathToMuscleGroups, contains(muscleGroups)));
	}

}
