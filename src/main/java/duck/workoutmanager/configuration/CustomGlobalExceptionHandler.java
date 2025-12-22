package duck.workoutmanager.configuration;

import duck.workoutmanager.application.domain.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {


    private ResponseEntity<Object> exceptionHandled(Exception exception, HttpStatus httpStatus) {
        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", new Date());
        body.put("status", httpStatus.value());
        body.put("errors", exception.getMessage());
        return new ResponseEntity<>(body, httpStatus);
    }

    @ExceptionHandler({DomainAttributeException.class})
    private ResponseEntity<Object> alreadyPresentProtocolException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler({AttributeIsNullException.class})
    private ResponseEntity<Object> attributeIsNullException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidUUIDException.class})
    private ResponseEntity<Object> invalidUUIDException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ObjectNotFoundException.class})
    private ResponseEntity<Object> objectNotFoundException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler({ObjectIsNullException.class})
    private ResponseEntity<Object> objectIsNullException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({SizeMismatchException.class})
    private ResponseEntity<Object> sizeMismatchException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({InvalidParsingException.class})
    private ResponseEntity<Object> invalidDateException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({AuthorizationException.class})
    private ResponseEntity<Object> authorizationException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    private ResponseEntity<Object> expiredJwtException(Exception exception) {
        return exceptionHandled(exception, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler({MalformedJwtException.class})
    private ResponseEntity<Object> exception(Exception exception) {
        return exceptionHandled(exception, HttpStatus.BAD_REQUEST);
    }
}
