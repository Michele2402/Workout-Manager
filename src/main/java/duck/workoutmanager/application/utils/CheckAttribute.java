package duck.workoutmanager.application.utils;

import duck.workoutmanager.application.domain.exception.AttributeIsNullException;
import duck.workoutmanager.application.domain.exception.InvalidSizeException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CheckAttribute {


    public void checkStringIsNotNullOrEmpty(String string, String infoAttribute) {
        if (string == null || string.isEmpty()) {
            log.error("{} is null or empty", infoAttribute);
            throw new AttributeIsNullException(infoAttribute + " is null or empty");
        }
    }

    public void checkStringIsShorterThan(String string, int maxLength, String infoAttribute) {
        if (string != null && string.length() > maxLength) {
            log.error("{} is longer than {}", infoAttribute, maxLength);
            throw new InvalidSizeException(infoAttribute + " is longer than " + maxLength);
        }
    }

    public void checkEmailFormat(String email, String infoAttribute) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        if (email != null && !email.matches(emailRegex)) {
            log.error("{} has invalid email format", infoAttribute);
            throw new InvalidSizeException(infoAttribute + " has invalid email format");
        }
    }
}
