package test.work.api.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.stream.Collectors;

import static org.springframework.core.Ordered.HIGHEST_PRECEDENCE;
import static org.springframework.http.HttpStatus.*;

@ControllerAdvice
@Order(HIGHEST_PRECEDENCE)
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {
            BadCredentialsException.class,
            UsernameNotFoundException.class
    })
    public ResponseEntity<?> badCredentialsException(RuntimeException exception, WebRequest request) {
        return getExceptionResponse(exception, request, UNAUTHORIZED);
    }

    @ExceptionHandler(value = {
            ConstraintViolationException.class,
            IllegalAccessException.class
    })
    public ResponseEntity<?> constraintViolationException(RuntimeException exception, WebRequest request) {
        return getExceptionResponse(exception, request, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException exception,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return getExceptionResponse(exception, request, BAD_REQUEST);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException exception,
            HttpHeaders httpHeaders,
            HttpStatus status,
            WebRequest request
    ) {
        var constraintViolations = exception.getBindingResult().getFieldErrors().stream()
                .map(f -> f.getField() + " : " + f.getDefaultMessage())
                .collect(Collectors.toSet());
        constraintViolations.addAll(exception.getBindingResult().getGlobalErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toSet()));
        return new ResponseEntity<>(
                new ExceptionResponse("Please check constraints violations.", request, constraintViolations),
                BAD_REQUEST);
    }

    private ResponseEntity<Object> getExceptionResponse(
            RuntimeException exception,
            WebRequest request,
            HttpStatus status
    ) {
        return new ResponseEntity<>(
                new ExceptionResponse(exception.getMessage(), request),
                status == null ? INTERNAL_SERVER_ERROR : status);
    }

}
