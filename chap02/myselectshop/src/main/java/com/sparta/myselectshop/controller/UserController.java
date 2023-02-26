package com.sparta.myselectshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

// 로그인, 회원가입 페이지를 가져오는 컨트롤러
@Controller // 자동 Bean 등록 (단순 페이지 반환만 하기 때문에 @Controller 사용)
@RequestMapping("/api/user")
public class UserController {

    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

}
/*
Controller -> Client request 받아서 Service단으로 보내주는 역할만 수행!!!
*/