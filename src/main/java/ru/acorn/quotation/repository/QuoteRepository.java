package ru.acorn.quotation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.acorn.quotation.entity.Quote;

@Repository
public interface QuoteRepository extends JpaRepository<Quote, Long> {
}
