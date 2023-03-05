package com.sparta.myselectshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api") // 클래스의 모든 요청은 우선 해당주소로 받음
public class ShopController {

    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index"); // index.html 실행
//    @GetMapping("/shop")
//    public ModelAndView shop() {
//        ModelAndView modelAndView = new ModelAndView("index");
//        modelAndView.addObject("username", "");
//        return modelAndView;
    }
}