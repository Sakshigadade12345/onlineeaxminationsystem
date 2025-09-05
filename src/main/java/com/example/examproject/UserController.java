package com.example.examproject;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
public class UserController {
    @Autowired
    private UserService userService;
      @PostMapping("/user-login")
    public ResponseEntity<String> login(@RequestBody User loginUser, HttpSession session) {
        List<User> users = userService.readUsers();

        for (User user : users) {
            if (user.getUserName().equals(loginUser.getUserName())
                    && user.getPassWord().equals(loginUser.getPassWord())) {
                session.setAttribute("userId", user.getUserId());
                return ResponseEntity.ok("Login successful");
            }
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
    }

    // ✅ GET SESSION USER
    @GetMapping("/session-user")
    public User getSessionUser(HttpSession session) {
        Integer userId = (Integer) session.getAttribute("userId");
        if (userId == null) return null;

        return userService.readUsersById(userId);
    }
    
    //read
    @GetMapping("Users")
   public List<User> fetchAllUsers(){
    return userService.readUsers();
   }
    @PostMapping("Users")
    public String createUsers(@RequestBody User users){
        return userService.createUsers(users);
    }
    //Delete
    @DeleteMapping("Users/{id}")
    public String deleteUsers(@PathVariable int id){
        if(userService.deleteUsers(id)){
            return "Deleted Successfully";
        }
        else{
            return "Deletion Failed";
        }
    }
      //read by id
    @GetMapping("SingleUsers")
    public User fetchUsersById(@RequestParam(required = true) int id){
        return userService.readUsersById(id);
    }
    //  search
    @GetMapping("Users/search")
    public User fetchUsersByIdAndName(@RequestParam int id,@RequestParam String name){
        return userService.getUsersByIdAndName(id ,name);
    }
    // update
    @PutMapping("Users/{id}")
    public String updateUsers(@PathVariable int id, @RequestBody User users){
       return userService.updateUsers(id,users);
    }
}
    


