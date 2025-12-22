package duck.workoutmanager.application.domain.exception;

public class InvalidUUIDException extends RuntimeException {
    public InvalidUUIDException(String message) {
        super(message);
    }
}
