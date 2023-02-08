package ru.acorn.quotation.service;

import org.springframework.stereotype.Service;
import ru.acorn.quotation.repository.QuoteRepository;

@Service
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
        }
    }

    public void removeScore(Long id) {
        var quoteToRemoveLike = quoteRepository.findById(id);
        if (quoteToRemoveLike.isPresent()) {
            var quoteToAddLikePersistent = quoteToRemoveLike.get();
            var score = quoteToAddLikePersistent.getScore() - 1;
            quoteToAddLikePersistent.setScore(score);
            quoteRepository.save(quoteToAddLikePersistent);
        }
    }
}
