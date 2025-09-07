package com.example.examproject;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "quiz_progress")
@Data
public class QuizProgressEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int userId;
    private String quizName;

    @Lob
    private String answersJson;
}
