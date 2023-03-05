package com.sparta.myselectshop.controller;

import com.sparta.myselectshop.dto.LoginRequestDto;
import com.sparta.myselectshop.dto.SignupRequestDto;
import com.sparta.myselectshop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 로그인, 회원가입 페이지를 가져오는 컨트롤러
@Controller // 자동 Bean 등록 (단순 페이지 반환만 하기 때문에 @Controller 사용)
@RequiredArgsConstructor // final로 선언된 멤버변수 자동생성(DI 의존성주입)
@RequestMapping("/api/user")
public class UserController {
    // Service단 객체로 연결
    private final UserService userService;

    // 회원가입 페이지 반환
    @GetMapping("/signup")
    public ModelAndView signupPage() {
        return new ModelAndView("signup");
    }

    // 로그인 페이지 반환
    @GetMapping("/login")
    public ModelAndView loginPage() {
        return new ModelAndView("login");
    }

    // 회원가입
    @PostMapping("/signup")
    public String signup(SignupRequestDto signupRequestDto) {
        userService.signup(signupRequestDto);
        // Service단에서 signup 메서드 실행(파라미터로 Client 입력값 signupRequestDto 넘김)
        return "redirect:/api/user/login";
        // 끝나고 나면 로그인 페이지로 redirect함 (POST FORM 태그 형식)
    }

    // 로그인
    @ResponseBody // ajax에서 body에 값이 넘어감
    @PostMapping("/login")
    public String login(@RequestBody LoginRequestDto loginRequestDto, HttpServletResponse response) {
        // HttpServletResponse의 response 객체에 토큰값을 Header에 담아 Client 쪽으로 반환함
        userService.login(loginRequestDto, response);
        return "success";
    }
//    @PostMapping("/login")
//    public String login(LoginRequestDto loginRequestDto) {
//        userService.login(loginRequestDto);
//        // Service단에서 login 메서드 실행(파라미터로 Client입력값 loginRequestDto 넘김)
//        return "redirect:/api/shop";
//        // 끝나고 나면 메인 페이지로 redirect함
//    }
//    POST Form 태그 -> ModelAttribute 형식으로 받아옴
}
/*
Controller -> Client request 받아서 Service단으로 보내주는 역할만 수행!!!
*/