package ru.acorn.quotation.utils;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import ru.acorn.quotation.dto.QuoteDto;
import ru.acorn.quotation.entity.Quote;

@Component
public class ModelMapperUtil {
    private final ModelMapper modelMapper;

    public ModelMapperUtil() {
        this.modelMapper = new ModelMapper();
    }

    public Quote convertFromQuoteDto(QuoteDto quoteDto){
       return modelMapper.map(quoteDto, Quote.class);
    }
}
