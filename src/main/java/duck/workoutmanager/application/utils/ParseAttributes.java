package duck.workoutmanager.application.utils;

import duck.workoutmanager.application.domain.enums.MuscleGroupEnum;
import duck.workoutmanager.application.domain.exception.InvalidParsingException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ParseAttributes {

    public MuscleGroupEnum parseMuscleGroup(String muscleGroupStr) {
        try {
            return MuscleGroupEnum.valueOf(muscleGroupStr.toUpperCase());
        } catch (IllegalArgumentException | NullPointerException e) {
            log.error("Invalid muscle group: {}", muscleGroupStr);
            throw new InvalidParsingException("Invalid muscle group: " + muscleGroupStr);
        }
    }
}
