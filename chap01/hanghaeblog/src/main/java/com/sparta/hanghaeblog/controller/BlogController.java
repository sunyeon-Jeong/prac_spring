package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.BlogRequestDto;
import com.sparta.hanghaeblog.dto.BlogResponseDto;
import com.sparta.hanghaeblog.entity.Blog;
import com.sparta.hanghaeblog.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController // Json 형태의 데이터 반환
@RequiredArgsConstructor // final, @Notnull -> 필드의 생성자 자동생성
public class BlogController {
    // DB연결
    private final BlogService blogService;

    // 게시글작성
    @PostMapping("/api/posts") // 생성 -> POST 형식
    public BlogResponseDto createBlog(@RequestBody BlogRequestDto requestDto) {
        // 느슨한결합 -> Entity 바로 반환하지 않고, DTO에 담아서 반환
        // Entity인 Memo가 반환타입 X -> BlogResponseDto가 반환타입!
        // @RequestBody : Client 입력값 -> HTTP BODY에 Json형태로 넘어감 -> 메서드 파라미터에 값을 받아올 객체를 지정
        return blogService.createBlog(requestDto); // DB에서 createBlog메서드 리턴 (requestDto 파라미터로 받음)
    }
}
