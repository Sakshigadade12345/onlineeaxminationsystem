package com.example.examproject;

import java.time.LocalDateTime;

import lombok.Data;
@Data
public class Exam {
    private int examId;
     private int userId;         
    private String examTitle;
    private int totalMarks;
   private LocalDateTime completedAt;
    private String result;
}


