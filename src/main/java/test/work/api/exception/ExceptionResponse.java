package test.work.api.exception;

import lombok.Data;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
public class ExceptionResponse {

    private LocalDateTime timestamp;
    private String message;
    private String path;
    private Collection<String> constraintViolations;

    ExceptionResponse(String message, WebRequest request) {
        this.timestamp = LocalDateTime.now();
        this.message = message;
        this.path = request.getDescription(false);
    }

    ExceptionResponse(String message, WebRequest request, Collection<String> constraintViolations) {
        this(message, request);
        this.constraintViolations = constraintViolations;
    }

}
