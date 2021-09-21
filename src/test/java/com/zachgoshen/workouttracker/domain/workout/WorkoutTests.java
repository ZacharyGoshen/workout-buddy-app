package com.zachgoshen.workouttracker.domain.workout;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

import com.zachgoshen.workouttracker.domain.workout.exercise.Exercise;
import com.zachgoshen.workouttracker.domain.workout.exercise.ExerciseDescription;
import com.zachgoshen.workouttracker.domain.workout.set.Set;
import com.zachgoshen.workouttracker.domain.workout.set.SingleExerciseSet;

public class WorkoutTests {
	
	@Test
	public void AddSet_OneSet_SetIsAdded() {
		Workout workout = new Workout();
		Set set = buildSet();
		
		workout.addSet(set, 0);
		
		assertEquals(1, workout.getSets().size());
		assertEquals(set, workout.getSets().get(0));
	}
	
	@Test
	public void AddSet_AppendMultipleSets_SetsAreListedInOrderTheyWereAdded() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		
		workout.addSet(set1, 0);
		workout.addSet(set2, 1);
		workout.addSet(set3, 2);
		
		assertEquals(3, workout.getSets().size());
		assertEquals(set1, workout.getSets().get(0));
		assertEquals(set2, workout.getSets().get(1));
		assertEquals(set3, workout.getSets().get(2));
	}
	
	@Test
	public void AddSet_PrependMultipleSets_SetsAreListedInReverseOrderTheyWereAdded() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		
		workout.addSet(set1, 0);
		workout.addSet(set2, 0);
		workout.addSet(set3, 0);
		
		assertEquals(3, workout.getSets().size());
		assertEquals(set3, workout.getSets().get(0));
		assertEquals(set2, workout.getSets().get(1));
		assertEquals(set1, workout.getSets().get(2));
	}
	
	@Test
	public void AddSet_MultipleSetsAddedInRandomOrder_SetsAreListedInCorrectOrder() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		Set set4 = buildSet();
		Set set5 = buildSet();
		
		workout.addSet(set1, 0);
		workout.addSet(set2, 0);
		workout.addSet(set3, 1);
		workout.addSet(set4, 2);
		workout.addSet(set5, 0);
		
		assertEquals(5, workout.getSets().size());
		assertEquals(set1, workout.getSets().get(4));
		assertEquals(set2, workout.getSets().get(1));
		assertEquals(set3, workout.getSets().get(2));
		assertEquals(set4, workout.getSets().get(3));
		assertEquals(set5, workout.getSets().get(0));
	}
	
	@Test
	public void AppendSet_OneSet_SetIsAdded() {
		Workout workout = new Workout();
		Set set = buildSet();
		
		workout.appendSet(set);
		
		assertEquals(1, workout.getSets().size());
		assertEquals(set, workout.getSets().get(0));
	}
	
	@Test
	public void AppendSet_MultipleSets_SetsAreListedInOrderTheyWereAppended() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		
		workout.appendSet(set1);
		workout.appendSet(set2);
		workout.appendSet(set3);
		
		assertEquals(3, workout.getSets().size());
		assertEquals(set1, workout.getSets().get(0));
		assertEquals(set2, workout.getSets().get(1));
		assertEquals(set3, workout.getSets().get(2));
	}
	
	@Test
	public void DuplicateSet_OneSetBefore_TwoSetsAfter() {
		Workout workout = new Workout();
		Set set = buildSet();
		workout.appendSet(set);
		
		workout.duplicateSet(0);
		
		assertEquals(2, workout.getSets().size());
	}
	
	@Test
	public void DuplicateSet_OneSetBefore_SetsAreDifferentObjects() {
		Workout workout = new Workout();
		Set set = buildSet();
		workout.appendSet(set);
		
		workout.duplicateSet(0);
		
		assertNotEquals(workout.getSets().get(0), workout.getSets().get(1));
	}
	
	@Test
	public void DuplicateLastSet_OneSetBefore_TwoSetsAfter() {
		Workout workout = new Workout();
		Set set = buildSet();
		workout.appendSet(set);
		
		workout.duplicateLastSet();
		
		assertEquals(2, workout.getSets().size());
	}
	
	@Test
	public void DuplicateLastSet_OneSetBefore_SetsAreDifferentObjects() {
		Workout workout = new Workout();
		Set set = buildSet();
		workout.appendSet(set);
		
		workout.duplicateLastSet();
		
		assertNotEquals(workout.getSets().get(0), workout.getSets().get(1));
	}
	
	@Test
	public void DuplicateLastSet_MultipleSets_CopyIsOfLastSet() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		set1.setTimeRested(45);
		workout.appendSet(set1);
		Set set2 = buildSet();
		set2.setTimeRested(60);
		workout.appendSet(set2);
		
		workout.duplicateLastSet();

		assertEquals(60, workout.getSets().get(2).getTimeRested().get());
		assertNotEquals(45, workout.getSets().get(2).getTimeRested().get());
	}
	
	@Test
	public void RemoveSet_RemoveOnlySet_WorkoutHasNoSets() {
		Workout workout = new Workout();
		Set set = buildSet();
		workout.addSet(set, 0);
		
		workout.removeSet(0);
		
		assertEquals(0, workout.getSets().size());
	}
	
	@Test
	public void RemoveSet_AddThreeSetsAndRemoveTheFirstSetThreeTimes_WorkoutHasNoSets() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		workout.addSet(set1, 0);
		workout.addSet(set2, 0);
		workout.addSet(set3, 0);
		
		workout.removeSet(0);
		workout.removeSet(0);
		workout.removeSet(0);
		
		assertEquals(0, workout.getSets().size());
	}
	
	@Test
	public void RemoveSet_AddThreeSetsAndRemoveTheLastSetThreeTimes_WorkoutHasNoSets() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		workout.addSet(set1, 0);
		workout.addSet(set2, 0);
		workout.addSet(set3, 0);
		
		workout.removeSet(2);
		workout.removeSet(1);
		workout.removeSet(0);
		
		assertEquals(0, workout.getSets().size());
	}
	
	@Test
	public void RemoveSet_AddFiveSetsAndRemoveAllInRandomOrder_WorkoutHasNoSets() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		Set set4 = buildSet();
		Set set5 = buildSet();
		workout.addSet(set1, 0);
		workout.addSet(set2, 1);
		workout.addSet(set3, 2);
		workout.addSet(set4, 3);
		workout.addSet(set5, 4);
		
		workout.removeSet(4);
		workout.removeSet(2);
		workout.removeSet(0);
		workout.removeSet(1);
		workout.removeSet(0);
		
		assertEquals(0, workout.getSets().size());
	}
	
	@Test
	public void RemoveSet_AddFiveSetsAndRemoveTwo_WorkoutHasThreeSets() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		Set set4 = buildSet();
		Set set5 = buildSet();
		workout.addSet(set1, 0);
		workout.addSet(set2, 1);
		workout.addSet(set3, 2);
		workout.addSet(set4, 3);
		workout.addSet(set5, 4);
		
		workout.removeSet(4);
		workout.removeSet(0);
		
		assertEquals(3, workout.getSets().size());
		assertEquals(set2, workout.getSets().get(0));
		assertEquals(set3, workout.getSets().get(1));
		assertEquals(set4, workout.getSets().get(2));
	}
	
	@Test
	public void MoveSet_MoveSetEarlier_SetsAreListedInCorrectOrder() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		Set set4 = buildSet();
		Set set5 = buildSet();
		workout.addSet(set1, 0);
		workout.addSet(set2, 1);
		workout.addSet(set3, 2);
		workout.addSet(set4, 3);
		workout.addSet(set5, 4);
		
		workout.moveSet(2, 0);
		
		assertEquals(5, workout.getSets().size());
		assertEquals(set1, workout.getSets().get(1));
		assertEquals(set2, workout.getSets().get(2));
		assertEquals(set3, workout.getSets().get(0));
		assertEquals(set4, workout.getSets().get(3));
		assertEquals(set5, workout.getSets().get(4));
	}
	
	@Test
	public void MoveSet_MoveSetToItsOwnPosition_SetsAreListedInTheSameOrderTheyBeganIn() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		Set set4 = buildSet();
		Set set5 = buildSet();
		workout.addSet(set1, 0);
		workout.addSet(set2, 1);
		workout.addSet(set3, 2);
		workout.addSet(set4, 3);
		workout.addSet(set5, 4);
		
		workout.moveSet(2, 2);
		
		assertEquals(5, workout.getSets().size());
		assertEquals(set1, workout.getSets().get(0));
		assertEquals(set2, workout.getSets().get(1));
		assertEquals(set3, workout.getSets().get(2));
		assertEquals(set4, workout.getSets().get(3));
		assertEquals(set5, workout.getSets().get(4));
	}
	
	@Test
	public void MoveSet_MoveSetLater_SetsAreListedInCorrectOrder() {
		Workout workout = new Workout();
		Set set1 = buildSet();
		Set set2 = buildSet();
		Set set3 = buildSet();
		Set set4 = buildSet();
		Set set5 = buildSet();
		workout.addSet(set1, 0);
		workout.addSet(set2, 1);
		workout.addSet(set3, 2);
		workout.addSet(set4, 3);
		workout.addSet(set5, 4);
		
		workout.moveSet(2, 4);
		
		assertEquals(5, workout.getSets().size());
		assertEquals(set1, workout.getSets().get(0));
		assertEquals(set2, workout.getSets().get(1));
		assertEquals(set3, workout.getSets().get(4));
		assertEquals(set4, workout.getSets().get(2));
		assertEquals(set5, workout.getSets().get(3));
	}
	
	private static Set buildSet() {
		ExerciseDescription description = new ExerciseDescription("description");
		Exercise exercise = new Exercise(description);
		return new SingleExerciseSet(exercise);
	}

}
