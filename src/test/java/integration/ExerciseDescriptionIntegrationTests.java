package integration;

import static com.zachgoshen.workoutbuddy.api.exercise.ExerciseDescriptionDtoAssertions.*;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDescriptionDto;
import com.zachgoshen.workoutbuddy.api.exercise.ExerciseDescriptionDtos;
import com.zachgoshen.workoutbuddy.api.exercise.MuscleGroupConverter;
import com.zachgoshen.workoutbuddy.domain.exercise.Exercise;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptions;
import com.zachgoshen.workoutbuddy.domain.set.SingleExerciseSet;
import com.zachgoshen.workoutbuddy.domain.workout.Workout;

public class ExerciseDescriptionIntegrationTests extends IntegrationTests {
	
	@Test
	public void Get_NoParameters_NoDescriptionsExist() throws Exception {
		performGet(null, null)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(0))));
	}
	
	@Test
	public void Get_NoParameters_SingleDescriptionExists() throws Exception {
		ExerciseDescription description = ExerciseDescriptions.benchPress();
		exerciseDescriptionRepository.save(description);
		
		ResultActions resultActions = performGet(null, null)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(1))));
		
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, description, 0);
	}
	
	@Test
	public void Get_NoParameters_MultipleDescriptionsExist() throws Exception {
		ExerciseDescription squat = ExerciseDescriptions.squat();
		exerciseDescriptionRepository.save(squat);
		
		ExerciseDescription benchPress = ExerciseDescriptions.benchPress();
		exerciseDescriptionRepository.save(benchPress);
		
		ExerciseDescription deadlift = ExerciseDescriptions.squat();
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
		ExerciseDescription squat = ExerciseDescriptions.squat();
		exerciseDescriptionRepository.save(squat);
		
		ExerciseDescription benchPress = ExerciseDescriptions.benchPress();
		exerciseDescriptionRepository.save(benchPress);
		
		ExerciseDescription deadlift = ExerciseDescriptions.squat();
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
		ExerciseDescription squat = ExerciseDescriptions.squat();
		exerciseDescriptionRepository.save(squat);
		
		ExerciseDescription benchPress = ExerciseDescriptions.benchPress();
		exerciseDescriptionRepository.save(benchPress);
		
		ExerciseDescription deadlift = ExerciseDescriptions.squat();
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
		ExerciseDescription squat = ExerciseDescriptions.squat();
		exerciseDescriptionRepository.save(squat);
		
		ExerciseDescription benchPress = ExerciseDescriptions.benchPress();
		exerciseDescriptionRepository.save(benchPress);
		
		ExerciseDescription deadlift = ExerciseDescriptions.squat();
		exerciseDescriptionRepository.save(deadlift);
		
		ResultActions resultActions = performGet("bench", null)
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.size()", is(equalTo(1))));
		
		assertResponseContainsExerciseDescriptionAtIndex(resultActions, benchPress, 0);
	}
	
	@Test
	public void Post_ValidRequest() throws Exception {
		ExerciseDescriptionDto dto = ExerciseDescriptionDtos.benchPress();
		
		performPost(dto).andExpect(status().is2xxSuccessful());
		
		List<ExerciseDescription> allDescriptions = exerciseDescriptionRepository.findAll();
		ExerciseDescription newDescription = allDescriptions.get(0);
		
		assertExerciseDescriptionDtoMatchesExerciseDescription(dto, newDescription);
	}
	
	@Test
	public void Post_NullName() throws Exception {
		ExerciseDescriptionDto dto = ExerciseDescriptionDtos.benchPress();
		dto.setName(null);
		
		performPost(dto)
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("DTO Conversion"))))
			.andExpect(jsonPath("$.message", is(equalTo("Name can't be null"))));
	}
	
	@Test
	public void Patch_DescriptionExists() throws Exception {
		ExerciseDescription existingDescription = ExerciseDescriptions.benchPress();
		exerciseDescriptionRepository.save(existingDescription);
		
		ExerciseDescriptionDto updateDto = ExerciseDescriptionDtos.deadlift();
		
		String id = existingDescription.getId();
		performPatch(id, updateDto).andExpect(status().is2xxSuccessful());
		
		List<ExerciseDescription> allDescriptions = exerciseDescriptionRepository.findAll();
		ExerciseDescription updatedDescription = allDescriptions.get(0);
		
		assertExerciseDescriptionUpdateDtoWasAppliedToExerciseDescription(updateDto, updatedDescription);
	}
	
	@Test
	public void Patch_NullName() throws Exception {
		ExerciseDescription existingDescription = ExerciseDescriptions.benchPress();
		exerciseDescriptionRepository.save(existingDescription);
		
		ExerciseDescriptionDto dto = ExerciseDescriptionDtos.deadlift();
		dto.setName(null);

		String id = existingDescription.getId();
		performPatch(id, dto)
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("DTO Conversion"))))
			.andExpect(jsonPath("$.message", is(equalTo("Name can't be null"))));
	}
	
	@Test
	public void Patch_DescriptionDoesntExist() throws Exception {
		ExerciseDescriptionDto dto = ExerciseDescriptionDtos.benchPress();

		performPatch("nonexistent-id", dto)
			.andExpect(status().is5xxServerError())
			.andExpect(jsonPath("$.type", is(equalTo("Nonexistent Exercise Description"))))
			.andExpect(jsonPath("$.message", is(equalTo("No exercise description exists with id 'nonexistent-id'"))));
	}

	@Test
	public void Delete_DescriptionExists() throws Exception {
		ExerciseDescription existingDescription = ExerciseDescriptions.benchPress();
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
		ExerciseDescription description = ExerciseDescriptions.benchPress();
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
	
	private static String buildPostOrPatchRequestBody(ExerciseDescriptionDto dto) {
		String name = dto.getName();
		String nameAsJsonValue = convertStringToJsonValue(name);
		
		String notes = dto.getNotes();
		String notesAsJsonValue = convertStringToJsonValue(notes);
		
		List<String> muscleGroups = dto.getMuscleGroups();
		String muscleGroupsAsJsonValue = convertListOfStringsToJsonList(muscleGroups);
		
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
	
	private ResultActions performDelete(String id) throws Exception {
		String url = String.format("/api/exercise-descriptions/%s", id);
		
		MockHttpServletRequestBuilder requestBuilder = delete(url);
		
		return mockMvc.perform(requestBuilder);
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
		String name = description.getName();
		assertResponseHasValueAtJsonPath(resultActions, pathToName, name);

		String pathToNotes = pathToExerciseDescription + ".notes";
		String notes = description.getNotes();
		assertResponseHasValueAtJsonPath(resultActions, pathToNotes, notes);
		
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
