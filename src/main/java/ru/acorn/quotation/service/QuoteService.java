package ru.acorn.quotation.service;

import jakarta.transaction.Transactional;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.acorn.quotation.entity.Quote;
import ru.acorn.quotation.repository.QuoteRepository;
import ru.acorn.quotation.repository.UserRepository;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class QuoteService {

    private final QuoteRepository quoteRepository;
    private final UserRepository userRepository;

    public QuoteService(QuoteRepository quoteRepository, UserRepository userRepository) {
        this.quoteRepository = quoteRepository;
        this.userRepository = userRepository;
    }

    public void saveQuote(Quote quote) {
        quote.setUser(userRepository.findByEmail(quote.getUser().getEmail()).get());
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

    public List<Quote> getAll(boolean orderByTop) {
        if (orderByTop)
            return quoteRepository.findAll(Sort.by(Sort.Direction.DESC, "quoteLike"));
        else
            return quoteRepository.findAll();
    }


    public List<Quote> getQuotesByTop(Integer page, Integer limit, boolean orderByTop) {
        if (orderByTop) {
            return quoteRepository.findAll(PageRequest.of
                    (page, limit, Sort.by(Sort.Direction.DESC, "quoteLike"))).getContent();
        } else {
            return quoteRepository.findAll(PageRequest.of
                    (page, limit)).getContent();
        }
    }

    public List<Quote> getQuotesByFlop(Integer page, Integer limit, boolean orderByFlop) {
        if (orderByFlop) {
            return quoteRepository.findAll(PageRequest.of
                    (page, limit, Sort.by(Sort.Direction.DESC, "quoteDislike"))).getContent();
        } else {
            return quoteRepository.findAll(PageRequest.of
                    (page, limit)).getContent();
        }
    }

    public List<Quote> getLastQuotes() {
        return quoteRepository.findAllByOrderByCreationTimeDesc();
    }

    public Optional <Quote> getRandomQuote() {
        var size = quoteRepository.findAll().size();

        var minSize = 1L;
        if ((long) size == minSize) {
            return quoteRepository.findById(1L);
        } else {
            var randomId = getRandomNumber((long) size);
            return quoteRepository.findById(randomId);
        }
    }

    public Long getRandomNumber(Long max) {
        var minValue = 1L;
        Random random = new Random();
        return random.longs(minValue, max).findFirst().getAsLong();
    }
}
