package ru.acorn.quotation.controller;

import org.springframework.http.HttpEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.acorn.quotation.dto.QuoteDto;
import ru.acorn.quotation.entity.Quote;
import ru.acorn.quotation.utils.ErrorsUtil;
import ru.acorn.quotation.utils.ModelMapperUtil;

@RestController
public class QuoteController {

    private final ModelMapperUtil modelMapperUtil;

    public QuoteController(ModelMapperUtil modelMapperUtil) {
        this.modelMapperUtil = modelMapperUtil;
    }

    @PostMapping
    public HttpEntity<?> createQuote(@RequestBody QuoteDto quoteDto,
                                     BindingResult bindingResult) {
        Quote quoteToSave = modelMapperUtil.convertFromQuoteDto(quoteDto);
        if(bindingResult.hasErrors()){
            ErrorsUtil.returnErrorMessage(bindingResult);
        }
        return null;
    }
}
