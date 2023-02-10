package com.sparta.springmvc;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/request") // request path로 시작
public class HelloRequestController {

    @GetMapping("/form/html") // hello-request-form html파일 호출
    public String helloForm() {
        return "hello-request-form";
    }

    @GetMapping("/star/{name}/age/{age}")
    @ResponseBody // View가 아니라, Json형태의 데이터만 반환
    public String helloRequestPath(@PathVariable String name, @PathVariable int age)
    {
        return String.format("Hello, @PathVariable.<br> name = %s, age = %d", name, age);
    } // @PathVariable 사용 -> URL으로 전달된 값, 파라미터로 받아옴

    @GetMapping("/form/param") // param?name=mallang&age=22 (쿼리스트링)
    @ResponseBody
    public String helloGetRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    } // @RequestParam 사용 -> URL으로 전달된 값, 파라미터로 받아옴, GET방식 -> URL에 값 표출

    @PostMapping("/form/param") // POST방식 -> URL에 값 표출X, 페이로드에 값이 들어가있음
    @ResponseBody
    public String helloPostRequestParam(@RequestParam String name, @RequestParam int age) {
        return String.format("Hello, @RequestParam.<br> name = %s, age = %d", name, age);
    }


    @PostMapping("/form/model")
    @ResponseBody
    public String helloRequestBodyForm(@ModelAttribute Star star) { // ModelAttribute 사용 -> 한번에 데이터를 다 가져옴
        // 객체형식으로 받아서 가져옴
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }

    @PostMapping("/form/json")
    @ResponseBody
    public String helloPostRequestJson(@RequestBody Star star) {
        // 값이 HTTP Body에 Json형태로 넘어감
        // @RequestBody + 값을 받아올 객체 지정
        return String.format("Hello, @RequestBody.<br> (name = %s, age = %d) ", star.name, star.age);
    }
}