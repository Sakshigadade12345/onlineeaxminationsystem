package com.example.examproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/quiz-progress")
public class QuizProgressController {

    @Autowired
    private QuizProgressService progressService;

    @PostMapping("/save")
    public void saveProgress(@RequestBody QuizProgress progress) {
        progressService.saveProgress(progress);
    }

    @GetMapping("/load")
    public QuizProgress loadProgress(@RequestParam int userId, @RequestParam String quizName) {
        return progressService.loadProgress(userId, quizName);
    }
}
