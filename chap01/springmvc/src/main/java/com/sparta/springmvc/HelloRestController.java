package com.sparta.springmvc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController // Json형태의 데이터 반환
@RequestMapping("/rest")
public class HelloRestController {

    @GetMapping("/json/string") // 아래 HTML 반환
    public String helloHtmlString() {
        return "<html><body>Hello @ResponseBody</body></html>";
    }

    @GetMapping("/json/list") // 리스트 반환
    public List<String> helloJson() {
        List<String> words = Arrays.asList("Hello", "Controller", "And", "JSON");

        return words;
    }
}

// @RestController 사용 -> ResponseBody 사용 하지 않아도 Error 없음