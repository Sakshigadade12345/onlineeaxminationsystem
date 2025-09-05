package com.example.examproject;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public String createUsers(User user) {
        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(user, userEntity);

        // If user has exams in request and you want to set them:
        if (user.getExams() != null) {
            ArrayList<ExamEntity> exams = new ArrayList<>();
            for (Exam exam : user.getExams()) {
                ExamEntity examEntity = new ExamEntity();
                examEntity.setExamTitle(exam.getExamTitle());
                examEntity.setTotalMarks(exam.getTotalMarks());
                examEntity.setUser(userEntity); // ✅ Set user in exam
                exams.add(examEntity);
            }
            userEntity.setExams(exams);
        }

        userRepository.save(userEntity);
        return "User created successfully";
    }
     @Override
    public List<User>readUsers(){
        List<UserEntity> userList=userRepository.findAll();
        List<User>users=new ArrayList<>();

        for(UserEntity usersEntity:userList){
            User user =new User();
            user.setUserId(usersEntity.getUserId());
            user.setUserName(usersEntity.getUserName());
            user.setPassWord(usersEntity.getPassWord());
            users.add(user);
        }
         return users;
        
    }
     @Override
        public boolean deleteUsers(int id){
            UserEntity userEntity=userRepository.findById(id).get();
           userRepository.delete(userEntity);
            return true;

        }
         @Override

        public User readUsersById(int id){
           UserEntity userEntity=userRepository.findById(id).orElse(null);
            if(userEntity==null){
                return null;
            }
             User user =new User();
             user.setUserId(userEntity.getUserId());
             user.setUserName(userEntity.getUserName());
             user.setPassWord(userEntity.getPassWord());
           
           
            return user ;
        }
         @Override
        public User getUsersByIdAndName(int id,String name){
            name=name.trim();
            UserEntity userEntity=userRepository.findByUserIdAndUserName(id, name);

            if(userEntity==null){
                return null;

            }
            User user =new User();
            user.setUserId(userEntity.getUserId());
            user.setUserName(userEntity.getUserName());
            user.setPassWord(userEntity.getPassWord());
            
           
            return user ;
        }
         @Override
    public String updateUsers(int id, User user) {
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if(userEntity==null){
            return "User not found";
        }
        
            userEntity.setUserId(user.getUserId());
            userEntity.setUserName(user.getUserName());
            userEntity.setPassWord(user.getPassWord());
        return " Updated Successfully";

    }
   
   
   
    

    


        
}
