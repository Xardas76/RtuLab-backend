package com.controlers;

import com.accounts.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {

    @PostMapping("/reg/{username}")
    public String registerUser(@PathVariable String username, Model model){
        User user = new User(username);
        model.addAttribute("user", user);
        return "success";
    }
}
