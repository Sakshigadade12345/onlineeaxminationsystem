package com.example.examproject;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer>{
     UserEntity findByUserIdAndUserName(int userId,String userName);

     UserEntity findByUserName(String username);
     

     
    
}
