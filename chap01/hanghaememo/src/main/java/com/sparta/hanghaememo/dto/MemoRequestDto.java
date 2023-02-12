package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter
public class MemoRequestDto {
    private String username;
    private String contents;
}
// 클라이언트를 통해 넘어오는 username, contents -> 객체로 받아옴