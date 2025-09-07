package com.example.examproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface QuizProgressRepository extends JpaRepository<QuizProgressEntity, Integer> {
    QuizProgressEntity findByUserIdAndQuizName(int userId, String quizName);
}
