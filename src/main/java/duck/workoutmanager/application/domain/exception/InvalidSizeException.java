package duck.workoutmanager.application.domain.exception;

public class InvalidSizeException extends RuntimeException {
    public InvalidSizeException(String message) {
        super(message);
    }
}
