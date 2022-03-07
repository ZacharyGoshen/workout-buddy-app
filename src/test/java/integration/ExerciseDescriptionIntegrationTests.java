package integration;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;

import com.zachgoshen.workoutbuddy.WorkoutBuddyApplication;
import com.zachgoshen.workoutbuddy.api.exercise.MuscleGroupConverter;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescription;
import com.zachgoshen.workoutbuddy.domain.exercise.description.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

@SpringBootTest(classes = WorkoutBuddyApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class ExerciseDescriptionIntegrationTests {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Autowired
	private ExerciseDescriptionRepository exerciseDescriptionRepository;
	
	@BeforeEach
	public void deleteAllData() {
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
	
	private ResultActions performGet(String name, String sortBy) throws Exception {
		MockHttpServletRequestBuilder requestBuilder = get("/api/exercise-descriptions")
			.param("name", name)
			.param("sortBy", sortBy);
		
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
