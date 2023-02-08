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

    @PostMapping("/add-score/{id}")
    public HttpEntity<HttpStatus> addScore(@PathVariable Long id){
        scoreService.addScore(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/remove-score/{id}")
    public HttpEntity<HttpStatus> removeScore (@PathVariable Long id){
        scoreService.removeScore(id);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
