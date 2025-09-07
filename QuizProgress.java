package com.example.examproject;

import lombok.Data;

@Data
public class QuizProgress {
    private int userId;
    private String quizName;
    private String answersJson;
}
