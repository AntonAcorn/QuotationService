package ru.acorn.quotation.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.acorn.quotation.entity.User;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class QuoteDto {
    private String content;
    private Long score = 0L;
    private User user;
}
