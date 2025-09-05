package com.example.examproject;

import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamServiceImpl implements ExamService {
 @Autowired
    private ExamRepository examRepository;
@Autowired
    private UserRepository userRepository;  
     
@Override
public String createExam(Exam exam) {
    // 1. Fetch the UserEntity from DB
    UserEntity user = userRepository.findById(exam.getUserId()).orElse(null);
    if (user == null) {
        return "User not found!";
    }

    // 2. Manually populate ExamEntity
    ExamEntity examEntity = new ExamEntity();
    examEntity.setUser(user);
    examEntity.setExamTitle(exam.getExamTitle());

    // You can calculate totalMarks if needed or use directly
    examEntity.setTotalMarks(exam.getTotalMarks());
    examEntity.setCompletedAt(exam.getCompletedAt());
    examEntity.setResult(exam.getResult());

    // 3. Save the entity
    examRepository.save(examEntity);

    return "Exam data saved!";
}

        
     @Override
public List<Exam> readExams() {
    List<ExamEntity> examList = examRepository.findAll();
    List<Exam> exams = new ArrayList<>();

    for (ExamEntity examEntity : examList) {
        Exam exam = new Exam();
        exam.setExamId(examEntity.getExamId());
        exam.setExamTitle(examEntity.getExamTitle());
        exam.setTotalMarks(examEntity.getTotalMarks());
        exam.setCompletedAt(examEntity.getCompletedAt());
        exam.setResult(examEntity.getResult());
        
        // ✅ Fix: set userId manually
        if (examEntity.getUser() != null) {
            exam.setUserId(examEntity.getUser().getUserId());
        }

        exams.add(exam);
    }
    return exams;
}

     @Override
        public boolean deleteExam(int id){
            ExamEntity examEntity=examRepository.findById(id).get();
           examRepository.delete(examEntity);
            return true;

        }
         @Override

        public Exam readExamById(int id){
           ExamEntity examEntity=examRepository.findById(id).orElse(null);
            if(examEntity==null){
                return null;
            }
             Exam exam =new Exam();
             exam.setExamId(examEntity.getExamId());
            
             
            exam.setExamTitle(examEntity.getExamTitle());
            exam.setTotalMarks(examEntity.getTotalMarks());
             if (examEntity.getUser() != null) {
            exam.setUserId(examEntity.getUser().getUserId());
            exam.setCompletedAt(examEntity.getCompletedAt());
            exam.setResult(examEntity.getResult());
             
             }
             
            return exam ;
        }
         @Override
        public Exam getExamByIdAndUser_UserId(int id,int userId){
            
             ExamEntity examEntity = examRepository.findByExamIdAndUser_UserId(id,userId);
            if(examEntity==null){
                return null;

            }
            
            Exam exam =new Exam();
            exam.setExamId(examEntity.getExamId());
             
            
            exam.setExamTitle(examEntity.getExamTitle());
            exam.setTotalMarks(examEntity.getTotalMarks());
            if (examEntity.getUser() != null) {
            exam.setUserId(examEntity.getUser().getUserId());
            exam.setCompletedAt(examEntity.getCompletedAt());
            exam.setResult(examEntity.getResult());
             
            }
            
            return exam ;
        }
         @Override
    public String updateExam(int id, Exam exam) {
        ExamEntity examEntity = examRepository.findById(id).orElse(null);
        if(examEntity==null){
            return "User not found";
        }
            exam.setExamId(examEntity.getExamId());
             examEntity.setExamTitle(exam.getExamTitle());
             examEntity.setTotalMarks(exam.getTotalMarks());
             examEntity.setCompletedAt(exam.getCompletedAt());
             examEntity.setResult(exam.getResult());

             UserEntity user = userRepository.findById(exam.getUserId()).orElse(null);
        if (user == null) {
            return "User not found!";
        }
        examEntity.setUser(user);
    
        return " Updated Successfully";

    }


       


        
   
   
   
    

    


        
}

