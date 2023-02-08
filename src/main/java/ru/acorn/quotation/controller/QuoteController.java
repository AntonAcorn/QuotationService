package ru.acorn.quotation.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.acorn.quotation.dto.QuoteDto;
import ru.acorn.quotation.entity.Quote;
import ru.acorn.quotation.service.QuoteService;
import ru.acorn.quotation.utils.ErrorsUtil;
import ru.acorn.quotation.utils.ModelMapperUtil;

@RestController
@Log4j
public class QuoteController {

    private final QuoteService quoteService;
    private final ModelMapperUtil modelMapperUtil;

    public QuoteController(QuoteService quoteService,
                           ModelMapperUtil modelMapperUtil) {
        this.quoteService = quoteService;
        this.modelMapperUtil = modelMapperUtil;
    }

    @PostMapping
    public HttpEntity<HttpStatus> createQuote(@RequestBody QuoteDto quoteDto,
                                     BindingResult bindingResult) {
        Quote quoteToSave = modelMapperUtil.convertFromQuoteDto(quoteDto);
        if(bindingResult.hasErrors()){
            ErrorsUtil.returnErrorMessage(bindingResult);
            log.debug(bindingResult);
        }
        quoteService.createQuote(quoteToSave);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
