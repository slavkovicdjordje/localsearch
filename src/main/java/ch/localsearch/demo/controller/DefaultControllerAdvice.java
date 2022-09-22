package ch.localsearch.demo.controller;

import ch.localsearch.demo.model.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@ControllerAdvice
public class DefaultControllerAdvice {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<ErrorResponse> handleHttpClientErrorException(HttpClientErrorException e) {
        if (NOT_FOUND.equals(e.getStatusCode())) {
            return new ResponseEntity<>(new ErrorResponse("Element not found"), NOT_FOUND);
        }
        return new ResponseEntity<>(new ErrorResponse("Internal server error"), INTERNAL_SERVER_ERROR);
    }

}