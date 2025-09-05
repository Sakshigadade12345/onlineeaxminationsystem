package com.example.examproject;


import java.util.List;

import lombok.Data;

@Data
public class User {
    private int userId;
    private String userName;
    private String passWord;
    private List<Exam> exams;  // ✅ हे field हवंच आहे

  
}
