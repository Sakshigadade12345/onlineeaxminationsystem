package com.example.examproject;





import lombok.Data;



@Data
public class SaveAnswerRequest {
    private int examId;
    private int questionId;
    private String userAnswer;
}

