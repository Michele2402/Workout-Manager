package duck.workoutmanager.application.domain.exception;

public class AttributeIsNullException extends RuntimeException {
    public AttributeIsNullException(String message) {
        super(message);
    }
}
