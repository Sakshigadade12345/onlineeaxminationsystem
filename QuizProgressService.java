package com.example.examproject;

public interface QuizProgressService {
    void saveProgress(QuizProgress progress);
    QuizProgress loadProgress(int userId, String quizName);
}
