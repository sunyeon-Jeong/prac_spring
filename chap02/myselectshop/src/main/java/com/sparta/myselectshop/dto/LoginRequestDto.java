package com.sparta.myselectshop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LoginRequestDto { // 로그인 dto
    private String username;
    private String password;
}