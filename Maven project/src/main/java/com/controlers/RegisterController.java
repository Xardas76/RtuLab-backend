package com.controlers;

import com.accounts.UserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegisterController {

    @PostMapping("/reg/{username}")
    public String registerUser(@PathVariable String username, Model model){
        UserProfile user = new UserProfile(username);
        model.addAttribute("user", user);
        return "success";
    }
}
