package com.example.examproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;

@Controller
public class LoginRegisterController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                                @RequestParam String password,
                                @RequestParam String confirm_password) {

        if (!password.equals(confirm_password)) {
            return "redirect:/register.html?error=password_mismatch";
        }

        if (userRepository.findByUserName(username) != null) {
            return "redirect:/register.html?error=username_taken";
        }

        UserEntity user = new UserEntity();
        user.setUserName(username);
        user.setPassWord(password);
        userRepository.save(user);

        return "redirect:/login.html"; // success
    }

    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                             @RequestParam String password,
                             HttpSession session) {

        UserEntity user = userRepository.findByUserName(username);
        if (user != null && user.getPassWord().equals(password)) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("userName", user.getUserName());
            return "redirect:/dashboard.html";
        }

        return "redirect:/login.html?error=invalid";
    }
    @PostMapping("/forget-password")
public String resetPassword(@RequestParam String username,
                            @RequestParam String newPassword,
                            @RequestParam String confirmPassword) {
    if (!newPassword.equals(confirmPassword)) {
        return "redirect:/forget-password.html?error=password_mismatch";
    }

    UserEntity user = userRepository.findByUserName(username);
    
    if (user == null) {
        return "redirect:/forget-password.html?error=user_not_found";
    }

    user.setPassWord(newPassword);
    userRepository.save(user);

    return "redirect:/login.html?message=password_reset_success";
}

}
