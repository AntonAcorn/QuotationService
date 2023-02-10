package ru.acorn.quotation.service;

import jakarta.transaction.Transactional;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import ru.acorn.quotation.exception.QuoteNotFoundException;
import ru.acorn.quotation.repository.QuoteRepository;

@Service
@Log4j
public class ScoreService {

    private final QuoteRepository quoteRepository;

    public ScoreService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void addLike(Long id) {
        var quoteToAddLike = quoteRepository.findById(id);
        if (quoteToAddLike.isPresent()) {
            var quoteToAddLikePersistent = quoteToAddLike.get();
            var score = quoteToAddLikePersistent.getQuoteLike() + 1;
            quoteToAddLikePersistent.setQuoteLike(score);
            quoteRepository.save(quoteToAddLikePersistent);
        }else {
            var message = "Quote is not found";
            log.debug(message);
            throw new QuoteNotFoundException(message);
        }
    }

    public void addDislike(Long id) {
        var quoteToRemoveLike = quoteRepository.findById(id);
        if (quoteToRemoveLike.isPresent()) {
            var quoteToAddLikePersistent = quoteToRemoveLike.get();
            var score = quoteToAddLikePersistent.getQuoteDislike() - 1;
            quoteToAddLikePersistent.setQuoteDislike(score);
            quoteRepository.save(quoteToAddLikePersistent);
        }else {
            var message = "Quote is not found";
            log.debug(message);
            throw new QuoteNotFoundException(message);
        }
    }
}
