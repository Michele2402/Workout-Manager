package duck.workoutmanager.application.domain.exception;

public class ObjectIsNullException extends RuntimeException {
    public ObjectIsNullException(String message) {
        super(message);
    }
}
