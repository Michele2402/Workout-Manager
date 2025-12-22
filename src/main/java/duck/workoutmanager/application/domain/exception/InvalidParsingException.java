package duck.workoutmanager.application.domain.exception;

public class InvalidParsingException extends RuntimeException {
    public InvalidParsingException(String message) {
        super(message);
    }
}
