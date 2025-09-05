package com.example.examproject;

import java.util.List;

public interface UserService {
String createUsers(User users);
    List<User> readUsers();
    boolean deleteUsers(int id);
    String updateUsers(int id, User users);
   User readUsersById(int id);
    User getUsersByIdAndName(int id,String name); 
}