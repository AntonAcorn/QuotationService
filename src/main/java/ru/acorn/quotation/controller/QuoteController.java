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

import java.time.LocalDateTime;
import java.util.Optional;

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
        quoteToSave.setUser(quoteDto.getUser());
        if (bindingResult.hasErrors()) {
            ErrorsUtil.returnErrorMessage(bindingResult);
            log.debug(bindingResult);
        }
        quoteService.saveQuote(quoteToSave);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public HttpEntity<HttpStatus> editQuote(@PathVariable Long id,
                                            @RequestBody QuoteDto quoteDtoWithChanges) {

        Optional<Quote> quoteToChange = quoteService.getQuoteById(id);
        if (quoteToChange.isPresent()) {
            var persistentQuote = quoteToChange.get();
            persistentQuote.setContent(quoteDtoWithChanges.getContent());
            persistentQuote.setTimeOfUpdating(LocalDateTime.now());
            quoteService.saveQuote(persistentQuote);
        } else {
            var message = "Quote is not found";
            log.debug(message);
            throw new QuoteNotFoundException(message);
        }
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<HttpStatus> deleteQuote(@PathVariable Long id) {
        quoteService.deleteQuote(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public HttpEntity<?> getQuoteById(@PathVariable Long id) {
        var quoteById = quoteService.getQuoteById(id);
        if (quoteById.isPresent()) {
            var persistentQuote = quoteById.get();
            return ResponseEntity.ok().body(persistentQuote);
        } else {
            var message = "Quote is not found";
            log.debug(message);
            throw new QuoteNotFoundException(message);
        }
    }

    @GetMapping
    public HttpEntity<?> getAllQuotes() {
        var allQuotes = quoteService.getAllQuotes();
        if (allQuotes == null) {
            var message = "Quote is not found";
            log.debug(message);
            throw new QuoteNotFoundException(message);
        }
        return ResponseEntity.ok().body(allQuotes);
    }

    @GetMapping("/pagination")
    public HttpEntity<?> getAllQuotesWithPagination(@RequestParam(required = false) Integer page,
                                                    @RequestParam(required = false) Integer limit,
                                                    @RequestParam(required = false) Boolean orderByTop,
                                                    @RequestParam(required = false) Boolean orderByFlop) {
            if(orderByTop != null && orderByFlop == null){
                var sortedList = quoteService.getQuotesByTop(page, limit, orderByTop);
                return ResponseEntity.ok().body(sortedList);
            } else if (orderByTop == null && orderByFlop != null){
                var sortedList = quoteService.getQuotesByFlop(page, limit, orderByFlop);
                return ResponseEntity.ok().body(sortedList);
        }
        return ResponseEntity.ok().body(quoteService.getAllQuotes());
    }

    @GetMapping("/last")
    public HttpEntity<?> getAllQuotesByCreationTime() {
        var listOfOrderedByCreationTime = quoteService.getLastQuotes();
        return ResponseEntity.ok().body(listOfOrderedByCreationTime);
    }

    @GetMapping("/random")
    public HttpEntity<?> getRandomQuote() {
        var randomQuote = quoteService.getRandomQuote();
        if (randomQuote.isPresent()) {
            var persistentQuote = randomQuote.get();
            return ResponseEntity.ok().body(persistentQuote);
        } else {
            var message = "Quote is not found";
            log.debug(message);
            throw new QuoteNotFoundException(message);
        }
    }
}
