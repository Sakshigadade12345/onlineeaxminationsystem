package com.example.examproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class ExamController {
 @Autowired
    private ExamService examService;
    
    //read
    @GetMapping("Exam")
   public List<Exam> fetchAllExams(){
    return examService.readExams();
   }
    @PostMapping("Exam")
    public String createExam(@RequestBody Exam exam){
        return examService.createExam(exam);
    }
    //Delete
    @DeleteMapping("Exam/{id}")
    public String deleteExam(@PathVariable int id){
        if(examService.deleteExam(id)){
            return "Deleted Successfully";
        }
        else{
            return "Deletion Failed";
        }
    }
      //read by id
    @GetMapping("SingleExam")
    public Exam fetchExamById(@RequestParam(required = true) int id){
        return examService.readExamById(id);
    }
    //  search
    @GetMapping("Exam/search")
    public Exam fetchExamByIdAndUser_UserId(@RequestParam int id,@RequestParam int userId){
        return examService.getExamByIdAndUser_UserId(id,userId);
    }
    // update
    @PutMapping("Exam/{id}")
    public String updateExam(@PathVariable int id, @RequestBody Exam exam){
       return examService.updateExam(id,exam);
    }
    
}
    




