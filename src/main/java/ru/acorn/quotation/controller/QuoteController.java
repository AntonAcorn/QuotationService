package ru.acorn.quotation.controller;

import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.acorn.quotation.dto.QuoteDto;

@RestController
public class QuoteController {

    @PostMapping
    public HttpEntity<?> createQuote(@RequestBody QuoteDto quoteDto,
                                     BindingResult bindingResult){


    }
}
