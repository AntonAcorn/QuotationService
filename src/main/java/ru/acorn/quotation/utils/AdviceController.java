package ru.acorn.quotation.utils;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.acorn.quotation.exception.QuoteNotFoundException;

import java.time.LocalDateTime;

@ControllerAdvice
@Log4j
public class AdviceController {
    @ExceptionHandler(QuoteNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(QuoteNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                exception.getMessage(), LocalDateTime.now());
        log.debug(errorResponse);
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
