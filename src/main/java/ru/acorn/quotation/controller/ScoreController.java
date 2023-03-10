package ru.acorn.quotation.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.acorn.quotation.service.ScoreService;

@RestController
@Log4j
@RequestMapping("/quotes")
public class ScoreController {
    private final ScoreService scoreService;

    public ScoreController(ScoreService scoreService) {
        this.scoreService = scoreService;
    }

    @PostMapping("/add-like/{id}")
    public HttpEntity<HttpStatus> addScore(@PathVariable Long id){
        scoreService.addLike(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @PostMapping("/add-dislike/{id}")
    public HttpEntity<HttpStatus> removeScore (@PathVariable Long id){
        scoreService.addDislike(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
