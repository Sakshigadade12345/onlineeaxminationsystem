package com.example.examproject;


import java.util.List;



public interface ExamService {

    String createExam(Exam exam);              // Create a new exam
    List<Exam> readExams();                    // Get all exams
    boolean deleteExam(int id);               // Delete by ID
    String updateExam(int id, Exam exam);     // Update exam by ID
    Exam readExamById(int id);                // Get one exam by ID
      // (Optional) Get exam by title
    Exam getExamByIdAndUser_UserId(int id, int userId);
   
   
    
}
