package com.sparta.myselectshopbeta.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/api") // 클래스의 모든 요청은 우선 해당주소로 받음
public class ShopController {
    // 메인페이지
    @GetMapping("/shop")
    public ModelAndView shop() {
        return new ModelAndView("index"); // index 파일을 불러옴
    }
}