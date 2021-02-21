package com.controlers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FrontController {

    @GetMapping("/user/{userId}")
    public String getUserInfo(){
        System.out.println("Hello in console");
        return "index";
    }
}
