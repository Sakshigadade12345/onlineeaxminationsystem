package com.example.examproject;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ExamRepository extends JpaRepository<ExamEntity,Integer>{
 
     ExamEntity findByExamIdAndUser_UserId(int examId,int userId);
     
}
