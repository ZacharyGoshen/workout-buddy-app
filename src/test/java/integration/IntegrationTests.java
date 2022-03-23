package integration;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.TimeZone;
import java.util.stream.Collectors;

import org.junit.jupiter.api.AfterEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.zachgoshen.workoutbuddy.WorkoutBuddyApplication;
import com.zachgoshen.workoutbuddy.application.exercise.ExerciseDescriptionRepository;
import com.zachgoshen.workoutbuddy.application.workout.WorkoutRepository;

@SpringBootTest(classes = WorkoutBuddyApplication.class)
@ActiveProfiles("test")
@AutoConfigureMockMvc
public class IntegrationTests {
	
	@Autowired
	protected MockMvc mockMvc;
	
	@Autowired
	protected WorkoutRepository workoutRepository;
	
	@Autowired
	protected ExerciseDescriptionRepository exerciseDescriptionRepository;
	
	@AfterEach
	public void deleteAllData() {
		workoutRepository.deleteAll();
		exerciseDescriptionRepository.deleteAll();
	}
	
	protected static String convertStringToJsonValue(String value) {
		if (value == null) {
			return "null";
		} else {
			return String.format("\"%s\"", value);
		}
	}
	
	protected static String convertIntegerToJsonValue(Integer value) {
		if (value == null) {
			return "null";
		} else {
			return String.format("\"%d\"", value);
		}
	}
	
	protected static String convertFloatToJsonValue(Float value) {
		if (value == null) {
			return "null";
		} else {
			return String.format("\"%f\"", value);
		}
	}
	
	protected static String convertListOfStringsToJsonList(List<String> list) {
		if (list == null) {
			return "[]";
		} else {
			String joinedItems = list.stream()
				.map(muscleGroup -> String.format("\"%s\"", muscleGroup))
				.collect(Collectors.joining(","));
			
			return String.format("[%s]", joinedItems);
		}
	}
	
	protected static String convertListOfJsonValuesToJsonList(List<String> list) {
		if (list == null) {
			return "[]";
		} else {
			String joinedItems = list.stream()
				.map(muscleGroup -> String.format("%s", muscleGroup))
				.collect(Collectors.joining(","));
			
			return String.format("[%s]", joinedItems);
		}
	}
	
	protected static void assertResponseHasValueAtJsonPathIfOptionalIsPresent(ResultActions resultActions, String path, Optional<?> optional) throws Exception {
		if (optional.isPresent()) {
			assertResponseHasValueAtJsonPath(resultActions, path, optional.get());
		}
	}
	
	protected static void assertResponseHasValueAtJsonPath(ResultActions resultActions, String path, Object value) throws Exception {
		if (value instanceof Float) {
			Float valueAsFloat = (Float) value;
			double valueAsDouble = valueAsFloat.doubleValue();
			resultActions.andExpect(jsonPath(path, is(equalTo(valueAsDouble))));
			
		} else if (value instanceof Date) {
			Date valueAsDate = (Date) value;
			
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS+00:00");
			
			TimeZone gmt = TimeZone.getTimeZone("GMT");
			dateFormat.setTimeZone(gmt);
			
			String valueAsString = dateFormat.format(valueAsDate);
					
			resultActions.andExpect(jsonPath(path, is(equalTo(valueAsString))));
			
		} else {
			resultActions.andExpect(jsonPath(path, is(equalTo(value))));
		}
	}

}
