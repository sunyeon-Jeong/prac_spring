package com.sparta.hanghaeblog.dto;

import lombok.Getter;

// Client 요청으로 들어오는 정보들을 담는 DTO
@Getter
public class BlogRequestDto {
    private String title; // 제목
    private String user; // 사용자
    private String content; // 작성내용
    private String password; // 비밀번호
}