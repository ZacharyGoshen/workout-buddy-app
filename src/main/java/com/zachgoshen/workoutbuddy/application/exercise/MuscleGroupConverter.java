package com.zachgoshen.workoutbuddy.application.exercise;

import com.zachgoshen.workoutbuddy.application.DtoConversionException;
import com.zachgoshen.workoutbuddy.domain.exercise.description.MuscleGroup;

public class MuscleGroupConverter {
	
	public static String toString(MuscleGroup muscleGroup) {
		switch (muscleGroup) {
			case ABS:
				return "Abs";
			case BICEPS:
				return "Biceps";
			case CALVES:
				return "Calves";
			case DELTS:
				return "Delts";
			case GLUTES:
				return "Glutes";
			case HAMSTRINGS:
				return "Hamstrings";
			case LATS:
				return "Lats";
			case LOWER_BACK:
				return "Lower Back";
			case OBLIQUES:
				return "Obliques";
			case PECS:
				return "Pecs";
			case QUADS:
				return "Quads";
			case TRAPS:
				return "Traps";
			default:
				return "Triceps";
		}
	}
	
	public static MuscleGroup toEnum(String muscleGroup) throws DtoConversionException {
		switch (muscleGroup) {
			case "Abs":
				return MuscleGroup.ABS;
			case "Biceps":
				return MuscleGroup.BICEPS;
			case "Calves":
				return MuscleGroup.CALVES;
			case "Delts":
				return MuscleGroup.DELTS;
			case "Glutes":
				return MuscleGroup.GLUTES;
			case "Hamstrings":
				return MuscleGroup.HAMSTRINGS;
			case "Lats":
				return MuscleGroup.LATS;
			case "Lower Back":
				return MuscleGroup.LOWER_BACK;
			case "Obliques":
				return MuscleGroup.OBLIQUES;
			case "Pecs":
				return MuscleGroup.PECS;
			case "Quads":
				return MuscleGroup.QUADS;
			case "Traps":
				return MuscleGroup.TRAPS;
			case "Triceps":
				return MuscleGroup.TRICEPS;
			default:
				throw new DtoConversionException("Muscle group can't be converted");
		}
	}

}
