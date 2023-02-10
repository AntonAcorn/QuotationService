package ru.acorn.quotation.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.acorn.quotation.entity.Quote;
import ru.acorn.quotation.repository.QuoteRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class QuoteServiceTest {

    @Mock
    private QuoteRepository quoteRepository;
    @InjectMocks
    private QuoteService quoteService;

    @Test
    void deleteQuote() {
    }

    @Test
    void getQuoteById() {
        var quote = getOneMockProject();
        Long id = 1L;
        Mockito.when(quoteRepository.findById(id)).thenReturn(Optional.ofNullable(quote));
        quoteRepository.findById(id);
        Mockito.verify(quoteRepository).findById(id);
    }

    @Test
    void getAllQuotes() {
        var listOfProjects = getProjects();
        Mockito.when(quoteRepository.findAll()).thenReturn(listOfProjects);
        var result = quoteService.getAllQuotes();
        assertNotNull(result);
        assertEquals(listOfProjects, result);
    }


    @Test
    void getRandomQuote() {

    }

    @Test
    void getRandomNumber() {
    }

    private Quote getOneMockProject(){
        return Quote.builder()
                .id(1L)
                .user(null)
                .quoteLike(12L)
                .quoteDislike(5L)
                .content("Test")
                .creationTime(LocalDateTime.now())
                .build();
    }

    private List<Quote> getProjects() {
        Quote project1 = Quote.builder()
                .id(1L)
                .user(null)
                .quoteLike(12L)
                .quoteDislike(5L)
                .content("Test")
                .creationTime(LocalDateTime.now())
                .build();

        Quote project2 = Quote.builder()
                .id(2L)
                .user(null)
                .quoteLike(12L)
                .quoteDislike(5L)
                .content("Test2")
                .creationTime(LocalDateTime.now())
                .build();
        return List.of(project1, project2);
    }
}