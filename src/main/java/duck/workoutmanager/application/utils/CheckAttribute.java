package duck.workoutmanager.application.utils;

import duck.workoutmanager.application.domain.exception.AttributeIsNullException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CheckAttribute {


    public void checkStringIsNullOrEmpty(String string, String infoAttribute) {
        if (string == null || string.isEmpty()) {
            log.error("{} is null or empty", infoAttribute);
            throw new AttributeIsNullException(infoAttribute + " is null or empty");
        }
    }
}
