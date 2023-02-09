package ru.acorn.quotation.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.acorn.quotation.entity.Quote;
import ru.acorn.quotation.repository.QuoteRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void saveQuote(Quote quote) {
        quoteRepository.save(quote);
    }

    public void deleteQuote(Long id) {
        quoteRepository.deleteById(id);
    }

    public Optional<Quote> getQuoteById(Long id) {
        return quoteRepository.findById(id);
    }

    public List<Quote> getAllQuotes() {
        return quoteRepository.findAll();
    }

    public List<Quote> getQuotesByTopOrFlop(Integer page, Integer limit, boolean orderByTop, boolean orderByFlop) {
        if (orderByTop) {
            return quoteRepository.findAll(PageRequest.of
                    (page, limit, Sort.by(Sort.Direction.DESC, "score"))).getContent();
        }
        if (orderByFlop) {
            return quoteRepository.findAll(PageRequest.of
                    (page, limit, Sort.by(Sort.Direction.ASC, "score"))).getContent();
        }
        return quoteRepository.findAll();
    }

    public List<Quote> getLastQuotes() {
        return quoteRepository.findAllByOrderByCreationTimeDesc();
    }

    public Optional<Quote> getRandomQuote() {
        var size = quoteRepository.findAll().size();
        var minValue = 0L;
        var randomId = getRandomNumberUsingInts(minValue, (long) size);
        return quoteRepository.findById(randomId);
    }

    public Long getRandomNumberUsingInts(Long min, Long max) {
        var minValue = 0L;
        Random random = new Random();
        return random.longs(minValue, max)
                .findFirst()
                .getAsLong();
    }
}
