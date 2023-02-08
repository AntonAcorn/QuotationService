package ru.acorn.quotation.service;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import ru.acorn.quotation.entity.Quote;
import ru.acorn.quotation.repository.QuoteRepository;

import java.util.List;

@Service
public class QuoteService {
    private final QuoteRepository quoteRepository;

    public QuoteService(QuoteRepository quoteRepository) {
        this.quoteRepository = quoteRepository;
    }

    public void createQuote (Quote quote){
        quoteRepository.save(quote);
    }

    public List<Quote> getAllQuotes(){
       return quoteRepository.findAll();
    }

    public List<Quote> getQuotesByTopOrFlop(Integer page, Integer limit, boolean orderByTopOrFlop) {
        if(orderByTopOrFlop){
            return quoteRepository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.DESC,"score"))).getContent();
        }else{
            return quoteRepository.findAll(PageRequest.of(page, limit, Sort.by(Sort.Direction.ASC,"score"))).getContent();
        }
    }

    public void addScore(Long id){
        var quoteToAddLike = quoteRepository.findById(id);
        if (quoteToAddLike.isPresent()) {
            var quoteToAddLikePersistent = quoteToAddLike.get();
            var score = quoteToAddLikePersistent.getScore() + 1;
            quoteToAddLikePersistent.setScore(score);
            quoteRepository.save(quoteToAddLikePersistent);
        }
    }

    public void removeScore(Long id){
        var quoteToRemoveLike = quoteRepository.findById(id);
        if (quoteToRemoveLike.isPresent()) {
            var quoteToAddLikePersistent = quoteToRemoveLike.get();
            var score = quoteToAddLikePersistent.getScore() - 1;
            quoteToAddLikePersistent.setScore(score);
            quoteRepository.save(quoteToAddLikePersistent);
        }
    }
}
