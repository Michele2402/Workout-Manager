package duck.workoutmanager.application.utils;

import duck.workoutmanager.application.domain.enums.ExerciseStatusEnum;
import duck.workoutmanager.application.domain.enums.MuscleGroupEnum;
import duck.workoutmanager.application.domain.exception.InvalidParsingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.UUID;

@Component
@Slf4j
public class ParseAttributes {


    public UUID parseUUID(String uuidStr) {
        try {
            return UUID.fromString(uuidStr);
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error("Invalid UUID: {}", uuidStr);
            throw new InvalidParsingException("Invalid UUID: " + uuidStr);
        }
    }


    public LocalDate parseLocalDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr);
        } catch (DateTimeParseException | NullPointerException e) {
            log.error("Invalid date: {}", dateStr);
            throw new InvalidParsingException("Invalid date: " + dateStr);
        }
    }


    public MuscleGroupEnum parseMuscleGroup(String muscleGroupStr) {
        try {
            return MuscleGroupEnum.valueOf(muscleGroupStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error("Invalid muscle group: {}", muscleGroupStr);
            throw new InvalidParsingException("Invalid muscle group: " + muscleGroupStr);
        }
    }


    public ExerciseStatusEnum parseExerciseStatus(String statusStr) {
        try {
            return ExerciseStatusEnum.valueOf(statusStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error("Invalid exercise status: {}", statusStr);
            throw new InvalidParsingException("Invalid exercise status: " + statusStr);
        }
    }
}
