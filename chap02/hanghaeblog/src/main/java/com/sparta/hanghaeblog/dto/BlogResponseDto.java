package com.sparta.hanghaeblog.dto;

import com.sparta.hanghaeblog.entity.Blog;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class BlogResponseDto {
    private String title; // 제목
    private String username; // 사용자
    private String content; // 작성내용
    private Long id; // 아이디
    private LocalDateTime createdAt; // 생성된 시간
    private LocalDateTime modifiedAt; // 변경된 시간

    // ResponseDto의 멤버변수 <- Entity blog 클래스 값 getter(모든 수정, 변경이 끝난 값)
    @Builder
    public BlogResponseDto(Blog blog) {
        this.title = blog.getTitle();
        this.username = blog.getUsername();
        this.content = blog.getContent();
        this.id = blog.getId();
        this.createdAt = blog.getCreatedAt();
        this.modifiedAt = blog.getModifiedAt();
    }
}