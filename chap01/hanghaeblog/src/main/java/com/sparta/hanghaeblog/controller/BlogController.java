package com.sparta.hanghaeblog.controller;

import com.sparta.hanghaeblog.dto.BlogRequestDto;
import com.sparta.hanghaeblog.dto.BlogResponseDto;
import com.sparta.hanghaeblog.entity.Blog;
import com.sparta.hanghaeblog.service.BlogService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return blogService.createBlog(requestDto); // Service단에서 createBlog메서드 리턴 (requestDto 파라미터로 받음)
    }

    // 전체게시글목록조회
    @GetMapping("/api/posts") // 조회 -> GET 형식
    public List<BlogResponseDto> getBlog() { // 반환타입은 ResponseDto(Service단에서 그렇게 지정함)
        return blogService.getBlog(); // Service단에서 getBlog메서드 리턴
    }

    // 선택한게시글조회
    @GetMapping("/api/post/{id}") // 조회 -> GET 형식
    public BlogResponseDto getSelectedBlog(@PathVariable Long id) {
        // 느슨한결합 -> Entity 바로 반환하지 않고, DTO에 담아서 반환
        // @PathVariable : URL을 통해 전달된 값 -> 파라미터로 받아옴
        return blogService.getSelectedBlog(id);
        // Service단의 getSelectedBlog 메서드에 id값 넣어 return
    }

}