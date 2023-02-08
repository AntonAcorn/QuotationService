package ru.acorn.quotation.utils;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import ru.acorn.quotation.exception.QuoteNotFoundException;

import java.util.List;

public class ErrorsUtil {
    public static void returnErrorMessage(BindingResult bindingResult) {

            StringBuilder errorMessage = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMessage.append(error.getField())
                        .append(" - ")
                        .append(error.getDefaultMessage())
                        .append("; ");
            }
            throw new QuoteNotFoundException(errorMessage.toString());
        }
    }

