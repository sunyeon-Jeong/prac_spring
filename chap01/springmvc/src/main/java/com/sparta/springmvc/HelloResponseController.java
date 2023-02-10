package com.sparta.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/response") // 전체 url /response로 시작하게 함
public class HelloResponseController {

    private static long visitCount = 0; // 방문자수 증가시켜주는 변수

    @GetMapping("/html/redirect") // 해당주소로 요청이 들어왔을 때
    public String htmlFile() {
        return "redirect:/hello.html"; // static 디렉토리에 있는 hello.html을 반환
    }

    @GetMapping("/html/templates")
    public String htmlTemplates() {
        return "hello";
    }

    @ResponseBody
    @GetMapping("/body/html")
    public String helloStringHTML() { // return값이 html -> 바로 적용되어 나옴
        return "<!DOCTYPE html>" +
                "<html>" +
                "<head><meta charset=\"UTF-8\"><title>By @ResponseBody</title></head>" +
                "<body> Hello, 정적 웹 페이지!!</body>" +
                "</html>";
    }

    @GetMapping("/html/dynamic")
    public String helloHtmlFile(Model model) { // Model안에 우리가 원하는 값을 넣고, hello-visit.html을 반환
        visitCount++; // 방문자수 증가
        model.addAttribute("visits", visitCount);
        return "hello-visit"; // hello-visit.html에 방문자수 기록값
    }

    @ResponseBody
    @GetMapping("/json/string")
    public String helloStringJson() {
        return "{\"name\":\"르세라핌\",\"age\":20}"; // 콘솔 <body></body>에 json형태로 찍힘
    }

    @ResponseBody
    @GetMapping("/json/class")
    public Star helloJson() {
        return new Star("르세라핌", 20); // star 객체가 json 형태로 반환됨
    }
}