package ru.acorn.quotation.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.acorn.quotation.dto.QuoteDto;
import ru.acorn.quotation.entity.Quote;
import ru.acorn.quotation.exception.QuoteNotFoundException;
import ru.acorn.quotation.service.QuoteService;
import ru.acorn.quotation.utils.ErrorsUtil;
import ru.acorn.quotation.utils.ModelMapperUtil;

@RestController
@RequestMapping("/quotes")
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

    @GetMapping
    public HttpEntity<?> getAllQuotes (){
       var allQuotes = quoteService.getAllQuotes();
       if (allQuotes == null) {
           var message = "Quote is not found";
           log.debug(message);
           throw new QuoteNotFoundException(message);
       }
       return ResponseEntity.ok().body(allQuotes);
    }

    @GetMapping("/pagination")
    public HttpEntity<?> getAllQuotesWithPagination (@RequestParam Integer page,
                                                     @RequestParam Integer limit,
                                                     @RequestParam Boolean orderByTop,
                                                     @RequestParam Boolean orderByFlop){
        var sortedList= quoteService.getQuotesByTopOrFlop(page, limit, orderByTop, orderByFlop);
        return ResponseEntity.ok().body(sortedList);
    }

    @PostMapping("/add-score/{id}")
    public HttpEntity<HttpStatus> addScore(@PathVariable Long id){
        quoteService.addScore(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/remove-score/{id}")
    public HttpEntity<HttpStatus> removeScore (@PathVariable Long id){
        quoteService.removeScore(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }


}
