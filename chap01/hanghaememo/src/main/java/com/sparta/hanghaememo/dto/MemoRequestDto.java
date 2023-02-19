package com.sparta.hanghaememo.dto;

import lombok.Getter;

@Getter // 값을 가져옴
public class MemoRequestDto {
    private String username; // 사용자
    private String contents; // 내용
}
// 클라이언트를 통해 넘어오는 username, contents -> 객체로 받아옴
// @controller 클라이언트 -> MemoRequestDto 사용자, 내용 담아서 -> @service creatMemo 메서드에 전달