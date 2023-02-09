package ru.acorn.quotation.service;

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

    public void addScore(Long id) {
        var quoteToAddLike = quoteRepository.findById(id);
        if (quoteToAddLike.isPresent()) {
            var quoteToAddLikePersistent = quoteToAddLike.get();
            var score = quoteToAddLikePersistent.getScore() + 1;
            quoteToAddLikePersistent.setScore(score);
            quoteRepository.save(quoteToAddLikePersistent);
        }else {
            var message = "Quote is not found";
            log.debug(message);
            throw new QuoteNotFoundException(message);
        }
    }

    public void removeScore(Long id) {
        var quoteToRemoveLike = quoteRepository.findById(id);
        if (quoteToRemoveLike.isPresent()) {
            var quoteToAddLikePersistent = quoteToRemoveLike.get();
            var score = quoteToAddLikePersistent.getScore() - 1;
            quoteToAddLikePersistent.setScore(score);
            quoteRepository.save(quoteToAddLikePersistent);
        }else {
            var message = "Quote is not found";
            log.debug(message);
            throw new QuoteNotFoundException(message);
        }
    }
}
