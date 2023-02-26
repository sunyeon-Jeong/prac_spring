package com.sparta.myselectshop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class SignupRequestDto { // 회원가입 dto
    private String username;
    private String password;
    private String email;
    private boolean admin = false; // 관리자 아니기 때문에 false값 지정
    private String adminToken = "";
}