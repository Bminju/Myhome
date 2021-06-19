package com.mincoder.myhome.controller;

import com.mincoder.myhome.model.User;
import com.mincoder.myhome.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login(){
        return "account/login";
    }

    @GetMapping("/register")
    public String register(){

        return "account/register";
    }

    //회원가입
    @PostMapping("/register")
    public String register(User user){
        userService.save(user); //user 저장
        return "redirect:/"; //회원가입 완료 후 홈 이동
    }
}
