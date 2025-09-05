package com.example.examproject;




import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "users_db")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    private String userName;

    private String passWord;
     // One user → many exams
   @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ExamEntity> exams; // ✅ This should be List<ExamEntity>

}
