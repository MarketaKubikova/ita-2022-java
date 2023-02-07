package cz.vosickamarketa.ita.eshopbackend.rest;

import cz.vosickamarketa.ita.eshopbackend.exception.ItaException;
import cz.vosickamarketa.ita.eshopbackend.exception.enumeration.ErrorCode;
import cz.vosickamarketa.ita.eshopbackend.model.ExceptionDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@Slf4j
@Component
@ControllerAdvice
public class ItaCommonControllerAdvice extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ItaException.class)
    public ResponseEntity<Object> handleItaException(ItaException ex, ServletWebRequest request) {
        log.error("An exception has occurred while processing %s at %s".formatted(request.getRequest().getMethod(),
                request.getRequest().getRequestURL()));

        return handleExceptionInternal(
                ex,
                new ExceptionDto(ex.getMessage(), ex.getCode()),
                new HttpHeaders(),
                ex.getStatus(),
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception ex, ServletWebRequest request) {
        log.error("An exception has occurred while processing %s at %s".formatted(request.getRequest().getMethod(),
                request.getRequest().getRequestURL()));

        return handleExceptionInternal(
                ex,
                new ExceptionDto(ex.getMessage(), ErrorCode.UNKNOWN_EXCEPTION.getCode()),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {

        final String errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));

        log.error("An exception has occurred. Invalid argument.");

        return handleExceptionInternal(
                ex,
                new ExceptionDto(errors, ErrorCode.INVALID_ARGUMENT.getCode()),
                headers,
                status,
                request
        );
    }
}
