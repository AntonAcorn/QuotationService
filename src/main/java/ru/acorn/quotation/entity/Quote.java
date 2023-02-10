package ru.acorn.quotation.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CreationTimestamp;

import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "quote")
@Setter
@Getter
@EqualsAndHashCode(exclude = "id")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Quote implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "content")
    @NotEmpty(message = "quote should not be empty")
    private String content;

    @Column(name = "quote_like")
    private Long quoteLike = 0L;

    @Column(name = "quote_dislike")
    private Long quoteDislike = 0L;

    @CreationTimestamp
    @Column(name = "creation_time")
    private LocalDateTime creationTime;

    @CreationTimestamp
    @Column(name = "time_of_updating")
    private LocalDateTime timeOfUpdating;

    @ManyToOne
    @JoinColumn(name = "email", referencedColumnName = "email")
    private User user;
}
